package com.amaris.masa.inditex.controllers;

import com.amaris.masa.inditex.InditexApplication;
import com.amaris.masa.inditex.dtos.PriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = InditexApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/schema-h2.sql","/data-h2-tests.sql" })
public class PriceControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetPrices()
    {
        assertTrue(
        this.restTemplate.getForObject("http://localhost:" + port + "/prices/all", List.class)
            .size() == 4);
    }

    @Test
    public void testGetPriceByGetFirst()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-14-10.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "35.50");
    }

    @Test
    public void testGetPriceByGetSecond()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-14-16.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "25.45");
    }

    @Test
    public void testGetPriceByGetThird()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-14-21.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "35.50");
    }

    @Test
    public void testGetPriceByGetForth()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-15-10.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "30.50");
    }

    @Test
    public void testGetPriceByGetFifth()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-16-21.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "38.95");
    }

}
