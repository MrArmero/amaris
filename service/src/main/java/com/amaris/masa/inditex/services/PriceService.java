package com.amaris.masa.inditex.services;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.dtos.PriceRequest;
import com.amaris.masa.inditex.exceptions.RecordNotFoundException;
import com.amaris.masa.inditex.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceDTO getPriceByDateProductAndBrand(LocalDateTime date, int productId, int brandId) throws RecordNotFoundException  {
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(date, productId, brandId);

        if (prices == null || prices.isEmpty()) {
            throw new RecordNotFoundException("Price not found. Please retry with different values.");
        }

        Price firstPrice = prices.get(0);

        return new PriceDTO(firstPrice.getProductId(), firstPrice.getBrandId(),
                firstPrice.getId(), firstPrice.getStartDate(), firstPrice.getEndDate(),
                firstPrice.getAmount().toString(), firstPrice.getCurrency());
    }

    public PriceDTO getPriceByDateProductAndBrand(PriceRequest priceRequest) throws RecordNotFoundException {
        return getPriceByDateProductAndBrand(priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId());
    }

    public List<PriceDTO> getAll() {

        List<Price> prices = priceRepository.findAll();
        // If two prices have a temporary coincidence this method solve this conflict
        checkPeriodConflicts(prices);
        // Sorting list by priority in descending order
       return prices.stream().sorted(Comparator.comparingInt(Price::getPriority).reversed())
               .map( s -> new PriceDTO(s)).collect(Collectors.toList());
    }

    public List<PriceDTO> getDailyPriceList(PriceRequest priceRequest)  {
        return getDailyPriceList(priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId());
    }

    private List<PriceDTO> getDailyPriceList(LocalDateTime date, int productId, int brandId)  {
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(date, productId, brandId);
        // If two prices have a temporary coincidence this method solve this conflict
        checkPeriodConflicts(prices);

        return (prices == null || prices.isEmpty())?
                new ArrayList<>() :
                prices.stream().sorted(Comparator.comparingInt(Price::getPriority).reversed())
                        .map(s -> new PriceDTO(s)).collect(Collectors.toList());
    }

    private void checkPeriodConflicts(List<Price> prices) {
        if (prices!=null && !prices.isEmpty() && prices.size()>1 && hasAndSolveConflicts(prices)) {
            checkPeriodConflicts(prices);
        }
    }

    private boolean hasAndSolveConflicts(List<Price> prices) {
        boolean hasConflict = false;

        // Sorted by starting date
        prices.sort((p1,p2) -> p1.getStartDate().compareTo(p2.getStartDate()));

        for (int i=0; i < (prices.size()-1); i++) {
            Price leftP = prices.get(i);
            Price rightP = prices.get(i+1);
            if (rightP.getStartDate().compareTo(leftP.getEndDate())<0) {
                hasConflict=true;
                splitPeriod(prices,leftP,rightP);
                break;
            }
        }

        return hasConflict;
    }

    private void splitPeriod(List<Price> prices, Price leftP, Price rightP) {

        if (leftP.getPriority() < rightP.getPriority()
                && leftP.getEndDate().compareTo(rightP.getEndDate())>0) {
            // As right, with a bigger, price is included in range period of left we have to split it,
            // period of rightP.
            Price copyOfLeft = new Price(leftP);
            copyOfLeft.setStartDate(rightP.getEndDate());
            prices.add(copyOfLeft);
            leftP.setEndDate(rightP.getStartDate());
        } else if (leftP.getPriority() < rightP.getPriority()
                && leftP.getEndDate().compareTo(rightP.getEndDate())<=0) {
            leftP.setEndDate(rightP.getStartDate());
        } else if (leftP.getPriority() >= rightP.getPriority()
                && leftP.getEndDate().compareTo(rightP.getEndDate())>=0) {
            // Less priority and it is included in same period then it has no sense anymore.
            prices.remove(rightP);
        } else if (leftP.getPriority() >= rightP.getPriority()
                && leftP.getEndDate().compareTo(rightP.getEndDate())<0) {
            // Less priority and only partial overlapping
            rightP.setStartDate(leftP.getEndDate());
        }

    }
}
