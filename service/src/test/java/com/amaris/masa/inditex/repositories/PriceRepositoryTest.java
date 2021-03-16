package com.amaris.masa.inditex.repositories;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.utils.UtilsTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        assertEquals(4,priceRepository.findAll().size(), UtilsTesting.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandNotEmpty() throws ParseException {
        DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                sdf.parse("2020-06-18-01.01.00"),35455,1);

        assertFalse(CollectionUtils.isEmpty(prices), UtilsTesting.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandExpectedValue() throws ParseException {
        DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                sdf.parse("2020-06-18-01.01.00"),35455,1);

        assertEquals(CollectionUtils.firstElement(prices).getAmount(), BigDecimal.valueOf(38.95), UtilsTesting.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandExpectedValueSecondCase() throws ParseException {
        DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                sdf.parse("2020-06-14-17.01.00"),35455,1);

        assertEquals(CollectionUtils.firstElement(prices).getAmount(), BigDecimal.valueOf(25.45), UtilsTesting.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandExpectedValueThirdCase() throws ParseException {
        DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                sdf.parse("2020-06-15-11.00.00"),35455,1);

        assertEquals(CollectionUtils.firstElement(prices).getId(), 3, UtilsTesting.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByDateProductAndBrandEmpty() throws ParseException {
        DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        List<Price> prices = priceRepository.getPriceByDateProductAndBrand(
                sdf.parse("2023-06-15-11.00.00"),35455,1);

        assertTrue(CollectionUtils.isEmpty(prices), UtilsTesting.UNEXPECTED_VALUE);
    }
}