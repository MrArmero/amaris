package com.amaris.masa.inditex.services;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.dtos.PriceRequest;
import com.amaris.masa.inditex.exceptions.RecordNotFoundException;
import com.amaris.masa.inditex.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
       return priceRepository.findAll().stream().map(
                s -> new PriceDTO(s.getProductId(), s.getBrandId(), s.getId(),
                        s.getStartDate(), s.getEndDate(), s.getAmount().toString(), s.getCurrency())
        ).collect(Collectors.toList());
    }

    public List<PriceDTO> getDailyPriceList(PriceRequest priceRequest)  {
        return getDailyPriceList(priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId());
    }

    private List<PriceDTO> getDailyPriceList(LocalDateTime date, int productId, int brandId)  {
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(date, productId, brandId);

        return (prices == null || prices.isEmpty())?
                new ArrayList<>() :
                prices.stream().map(s -> new PriceDTO(s.getProductId(), s.getBrandId(), s.getId(),
                s.getStartDate(), s.getEndDate(), s.getAmount().toString(), s.getCurrency())).collect(Collectors.toList());
    }
}
