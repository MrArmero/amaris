package com.amaris.masa.inditex.controllers;

import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.services.PriceService;
import com.amaris.masa.inditex.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceServiceMock;

    @Test
    void getPriceByGet() throws Exception {
        PriceDTO priceDTO = new PriceDTO(35455, 1, 1, new Date(), new Date(), "82.93", "EUR");
        when(priceServiceMock.getPriceByDateProductAndBrand(any(Date.class), anyInt(), anyInt())).thenReturn(priceDTO);
        mockMvc.perform(get("/prices/find/2020-06-18-01.01.00/35455/1")).andDo(print()).andExpect(status().isOk());
        MvcResult requestResult = mockMvc.perform(get("/prices/find/2020-06-18-01.01.00/35455/1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        PriceDTO response = TestUtils.parseResponse(requestResult, PriceDTO.class);
        assertEquals(priceDTO.getPrice(), response.getPrice(), TestUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPriceByPost() throws Exception {
        PriceDTO priceDTO = new PriceDTO(35455, 1, 1, new Date(), new Date(), "82.93", "EUR");
        when(priceServiceMock.getPriceByDateProductAndBrand(any(Date.class), anyInt(), anyInt())).thenReturn(priceDTO);

        mockMvc.perform(post("/prices/find")
                    .param("date", "2020-06-18-01.01.00")
                    .param("productId", "35455")
                    .param("brandId", "1"))
                .andDo(print()).andExpect(status().isOk());


        MvcResult requestResult = mockMvc.perform(post("/prices/find")
                    .param("date", "2020-06-18-01.01.00")
                    .param("productId", "35455")
                    .param("brandId", "1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        PriceDTO response = TestUtils.parseResponse(requestResult, PriceDTO.class);
        assertEquals(priceDTO.getPrice(), response.getPrice(), TestUtils.UNEXPECTED_VALUE);
    }

    @Test
    void getPrices() throws Exception {
        when(priceServiceMock.getAll()).thenReturn(testPriceList());
        mockMvc.perform(get("/prices/all")).andDo(print()).andExpect(status().isOk());
    }

    private List<PriceDTO> testPriceList() {
        PriceDTO priceOne = new PriceDTO(1, 1, 1, new Date(), new Date(), "12.03", "GBP");
        PriceDTO priceTwo = new PriceDTO(2, 2, 2, new Date(), new Date(), "82.93", "EUR");
        PriceDTO priceThree = new PriceDTO(3, 3, 3, new Date(), new Date(), "19.08", "EUR");
        return Arrays.asList(priceOne, priceTwo, priceThree);
    }
}