package com.amaris.masa.inditex.services;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.dtos.PriceRequest;
import com.amaris.masa.inditex.exceptions.RecordNotFoundException;
import com.amaris.masa.inditex.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceDTO getPriceByDateProductAndBrand(Date date, int productId, int brandId) throws RecordNotFoundException  {
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(date, productId, brandId);

        if (CollectionUtils.isEmpty(prices)) {
            throw new RecordNotFoundException("Price not found. Please retry with different values.");
        }

        Price firstPrice = CollectionUtils.firstElement(prices);

        return new PriceDTO(firstPrice.getProduct().getId(), firstPrice.getBrand().getId(),
                firstPrice.getId(), firstPrice.getStartDate(), firstPrice.getEndDate(),
                firstPrice.getAmount().toString(), firstPrice.getCurrency());
    }

    public PriceDTO getPriceByDateProductAndBrand(PriceRequest priceRequest) throws RecordNotFoundException {
        return getPriceByDateProductAndBrand(priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId());
    }

    public List<PriceDTO> getAll() {
       return priceRepository.findAll().stream().map(
                s -> new PriceDTO(s.getProduct().getId(), s.getBrand().getId(), s.getId(),
                                s.getStartDate(), s.getEndDate(), s.getAmount().toString(),
                                s.getCurrency())
        ).collect(Collectors.toList());
    }
}
