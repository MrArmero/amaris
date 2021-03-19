package com.amaris.masa.inditex.controllers;

import com.amaris.masa.inditex.InditexConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = InditexConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private PriceController priceController;

    @Sql({"/schema-h2.sql","/data-h2-tests.sql" })
    @Test
    public void testGetPrices()
    {
        // TODO
    }

}
