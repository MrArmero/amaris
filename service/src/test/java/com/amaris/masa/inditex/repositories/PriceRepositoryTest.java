package com.amaris.masa.inditex.repositories;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.utils.TestinginditextUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Sql({"/schema-h2.sql","/data-h2-tests.sql" })
@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    @Test
    void findAll() {
        // Simplemente para validar que el sql esta cargado como corresponde en la BD
        assertEquals(4,priceRepository.findAll().size(), TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandNotEmpty() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                LocalDateTime.parse("2020-06-18-01.01.00", formatter),
                35455,1);

        assertFalse(CollectionUtils.isEmpty(prices), TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandExpectedValue() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                LocalDateTime.parse("2020-06-18-01.01.00", formatter),
                35455,1);

        assertEquals(CollectionUtils.firstElement(prices).getAmount(), BigDecimal.valueOf(38.95), TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandExpectedValueSecondCase() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                LocalDateTime.parse("2020-06-14-17.01.00", formatter),
                35455,1);

        assertEquals(CollectionUtils.firstElement(prices).getAmount(), BigDecimal.valueOf(25.45), TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandExpectedValueThirdCase() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                LocalDateTime.parse("2020-06-15-11.00.00", formatter),
                35455,1);

        assertEquals(CollectionUtils.firstElement(prices).getId(), 3, TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandEmpty() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                LocalDateTime.parse("2023-06-15-11.00.00", formatter),
                35455,1);

        assertTrue(CollectionUtils.isEmpty(prices), TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getNextPrices() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getNextPrices(LocalDateTime.parse("2020-06-15-11.00.00", formatter),35455,1);

        assertEquals(1, CollectionUtils.firstElement(prices).getId(), TestinginditextUtils.UNEXPECTED_VALUE);
        assertEquals(3, prices.size(), TestinginditextUtils.UNEXPECTED_VALUE);
    }
}