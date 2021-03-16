package com.amaris.masa.inditex.services;

import com.amaris.masa.inditex.repositories.PriceRepository;
import com.amaris.masa.inditex.utils.UtilsTesting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceServiceTest {

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    PriceService priceService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getPriceByDateProductAndBrand() {
    }

    @Test
    @DisplayName("List all prices stored in database")
    void getAllEmptyList() {
        assertTrue(CollectionUtils.isEmpty(priceService.getAll()), UtilsTesting.UNEXPECTED_VALUE);
    }
}