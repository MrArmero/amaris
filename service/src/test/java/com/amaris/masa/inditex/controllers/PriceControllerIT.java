package com.amaris.masa.inditex.controllers;

import com.amaris.masa.inditex.InditexApplication;
import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.dtos.PriceRequest;
import com.amaris.masa.inditex.utils.TestinginditextUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private PriceRequest priceRequestNow;
    private PriceRequest priceRequestCaseOne;
    private PriceRequest priceRequestCaseTwo;
    private PriceRequest priceRequestCaseThree;
    private PriceRequest priceRequestCaseFour;
    private PriceRequest priceRequestCaseFive;
    private int defaultBrand;
    private int defaultProduct;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        defaultBrand = 1;
        defaultProduct = 35455;
        priceRequestNow = new PriceRequest(LocalDateTime.now(), defaultBrand, defaultProduct);
        priceRequestCaseOne = new PriceRequest(LocalDateTime.of(2020,6,14,10,0), defaultProduct, defaultBrand);
        priceRequestCaseTwo = new PriceRequest(LocalDateTime.of(2020,6,14,16,0), defaultProduct, defaultBrand);
        priceRequestCaseThree = new PriceRequest(LocalDateTime.of(2020,6,14,21,0), defaultProduct, defaultBrand);
        priceRequestCaseFour = new PriceRequest(LocalDateTime.of(2020,6,15,10,0), defaultProduct, defaultBrand);
        priceRequestCaseFive = new PriceRequest(LocalDateTime.of(2020,6,16,21,0), defaultProduct, defaultBrand);

        mapper = new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .registerModule(new JavaTimeModule().addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"))))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
    }

    @Test
    public void testGetPrices()
    {
        assertTrue(
        this.restTemplate.getForObject("http://localhost:" + port + "/prices/all", List.class)
            .size() == 6, TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetPriceByGetFirst()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-14-10.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "35.50", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetPriceByGetSecond()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-14-16.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "25.45", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetPriceByGetThird()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-14-21.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "35.50", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetPriceByGetForth()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-15-10.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "30.50", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetPriceByGetFifth()
    {
        PriceDTO result = this.restTemplate.getForObject("http://localhost:" + port + "/prices/find/"
                + "2020-06-16-21.00.00/35455/1", PriceDTO.class);
        assertEquals(result.getPrice(), "38.95", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetNextPriceListByPost()
    {
        ResponseEntity<List> results = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find/list",
                priceRequestNow,
                List.class);
        assertTrue(results.getBody().isEmpty(), TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetNextPriceListByPostFirst()
    {
        ResponseEntity<List> results = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find/list",
                priceRequestCaseOne, List.class);

        assertFalse(results.getBody().isEmpty(), TestinginditextUtils.UNEXPECTED_VALUE);
        assertTrue(results.getBody().size()==6, TestinginditextUtils.UNEXPECTED_VALUE);

        List<PriceDTO> priceList = mapper.convertValue( results.getBody(), new TypeReference<List<PriceDTO>>(){});

        assertEquals(priceList.get(0).getPrice(), "35.50", TestinginditextUtils.UNEXPECTED_VALUE);
        assertEquals(priceList.get(1).getPrice(), "25.45", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetNextPriceListByPostSecond()
    {
        ResponseEntity<List> results = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find/list",
                priceRequestCaseTwo, List.class);

        assertFalse(results.getBody().isEmpty(), TestinginditextUtils.UNEXPECTED_VALUE);
        assertTrue(results.getBody().size()==5, TestinginditextUtils.UNEXPECTED_VALUE);

        List<PriceDTO> priceList = mapper.convertValue( results.getBody(), new TypeReference<List<PriceDTO>>(){});

        assertEquals(priceList.get(0).getPrice(), "25.45", TestinginditextUtils.UNEXPECTED_VALUE);
        assertEquals(priceList.get(1).getPrice(), "35.50", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetNextPriceListByPostThird()
    {
        ResponseEntity<List> results = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find/list",
                priceRequestCaseThree, List.class);

        assertFalse(results.getBody().isEmpty(), TestinginditextUtils.UNEXPECTED_VALUE);
        assertTrue(results.getBody().size()==4, TestinginditextUtils.UNEXPECTED_VALUE);

        List<PriceDTO> priceList = mapper.convertValue( results.getBody(), new TypeReference<List<PriceDTO>>(){});

        assertEquals(priceList.get(0).getPrice(), "35.50", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetNextPriceListByPostForth()
    {
        ResponseEntity<List> results = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find/list",
                priceRequestCaseFour, List.class);

        assertFalse(results.getBody().isEmpty(), TestinginditextUtils.UNEXPECTED_VALUE);
        assertTrue(results.getBody().size()==3, TestinginditextUtils.UNEXPECTED_VALUE);

        List<PriceDTO> priceList = mapper.convertValue( results.getBody(), new TypeReference<List<PriceDTO>>(){});

        assertEquals(priceList.get(0).getPrice(), "30.50", TestinginditextUtils.UNEXPECTED_VALUE);
        assertEquals(priceList.get(1).getPrice(), "35.50", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetNextPriceListByPostFifth()
    {
        ResponseEntity<List> results = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find/list",
                priceRequestCaseFive,
                List.class);

        assertFalse(results.getBody().isEmpty(), TestinginditextUtils.UNEXPECTED_VALUE);
        assertTrue(results.getBody().size()==1, TestinginditextUtils.UNEXPECTED_VALUE);

        assertTrue(results.getBody().get(0).toString().contains("38.95"), TestinginditextUtils.UNEXPECTED_VALUE);
        List<PriceDTO> priceList = mapper.convertValue( results.getBody(), new TypeReference<List<PriceDTO>>(){});
        assertEquals(priceList.get(0).getPrice(), "38.95", TestinginditextUtils.UNEXPECTED_VALUE);
    }

    @Test
    public void testGetPriceByPostOne()
    {
        ResponseEntity<PriceDTO> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find",
                priceRequestCaseOne, PriceDTO.class);

        assertNotNull(result.getBody(), TestinginditextUtils.UNEXPECTED_VALUE);
        PriceDTO price = mapper.convertValue( result.getBody(), new TypeReference<PriceDTO>(){});
        assertEquals( "35.50", price.getPrice(), TestinginditextUtils.UNEXPECTED_VALUE);

    }

    @Test
    public void testGetPriceByPostTwo()
    {
        ResponseEntity<PriceDTO> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find",
                priceRequestCaseTwo, PriceDTO.class);

        assertNotNull(result.getBody(), TestinginditextUtils.UNEXPECTED_VALUE);
        PriceDTO price = mapper.convertValue( result.getBody(), new TypeReference<PriceDTO>(){});
        assertEquals( "25.45", price.getPrice(), TestinginditextUtils.UNEXPECTED_VALUE);

    }

    @Test
    public void testGetPriceByPostThree()
    {
        ResponseEntity<PriceDTO> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find",
                priceRequestCaseThree, PriceDTO.class);

        assertNotNull(result.getBody(), TestinginditextUtils.UNEXPECTED_VALUE);
        PriceDTO price = mapper.convertValue( result.getBody(), new TypeReference<PriceDTO>(){});
        assertEquals( "35.50", price.getPrice(), TestinginditextUtils.UNEXPECTED_VALUE);

    }

    @Test
    public void testGetPriceByPostFour()
    {
        ResponseEntity<PriceDTO> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find",
                priceRequestCaseFour, PriceDTO.class);

        assertNotNull(result.getBody(), TestinginditextUtils.UNEXPECTED_VALUE);
        PriceDTO price = mapper.convertValue( result.getBody(), new TypeReference<PriceDTO>(){});
        assertEquals( "30.50", price.getPrice(), TestinginditextUtils.UNEXPECTED_VALUE);

    }

    @Test
    public void testGetPriceByPostFifth()
    {
        ResponseEntity<PriceDTO> result = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/prices/find",
                priceRequestCaseFive, PriceDTO.class);

        assertNotNull(result.getBody(), TestinginditextUtils.UNEXPECTED_VALUE);
        PriceDTO price = mapper.convertValue( result.getBody(), new TypeReference<PriceDTO>(){});
        assertEquals( "38.95", price.getPrice(), TestinginditextUtils.UNEXPECTED_VALUE);

    }
}
