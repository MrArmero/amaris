package com.amaris.masa.inditex.services;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.dtos.PriceRequest;
import com.amaris.masa.inditex.exceptions.RecordNotFoundException;
import com.amaris.masa.inditex.repositories.PriceRepository;
import com.amaris.masa.inditex.utils.TestinginditextUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceServiceTest {

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    PriceService priceService;

    private PriceRequest priceRequest;
    private int defaultBrand;
    private int defaultProduct;
    private LocalDateTime defaultDate;
    private List<Price> defaultPrices;

    @BeforeEach
    void setUp() {
        defaultBrand = 4;
        defaultProduct = 34;
        defaultDate = LocalDateTime.now();

        priceRequest = new PriceRequest();
        priceRequest.setBrandId(defaultBrand);
        priceRequest.setProductId(defaultProduct);
        priceRequest.setDate(defaultDate);

        Price price = new Price();
        price.setAmount(BigDecimal.ONE);
        price.setBrandId(defaultBrand);
        price.setProductId(defaultProduct);
        defaultPrices = new ArrayList<>();
        defaultPrices.add(price);
    }

    @Test
    @DisplayName("Finding action with a mocked result")
    void getPriceByDateProductAndBrand() throws RecordNotFoundException {
        when(priceRepository.getPriceByDateProductAndBrand(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(defaultPrices);
        PriceDTO priceDTO = priceService.getPriceByDateProductAndBrand(priceRequest);
        assertNotNull(priceDTO, TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    @DisplayName("Finding action without results is throwing a RecordNotFoundException")
    void getPriceByDateProductAndBrandThrowRecordNotFoundException() {
        Assertions.assertThrows(RecordNotFoundException.class, ()->{
            priceService.getPriceByDateProductAndBrand(priceRequest);
        });
    }

    @Test
    @DisplayName("List all prices stored in database")
    void getAllEmptyList() {
        assertTrue(CollectionUtils.isEmpty(priceService.getAll()), TestinginditextUtils.UNEXPECTED_VALUE);
    }
}