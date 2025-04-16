package com.example.prices;

import com.example.prices.controller.PriceController;
import com.example.prices.model.Price;
import com.example.prices.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private final String URL = "/api/prices";

    private Price buildMockPrice(int priceList, int productId, int brandId, double price) {
        Price mock = new Price();
        mock.setPriceList(priceList);
        mock.setProductId(productId);
        mock.setBrandId(brandId);
        mock.setStartDate(LocalDateTime.now());
        mock.setEndDate(LocalDateTime.now());
        mock.setPrice(BigDecimal.valueOf(price));
        mock.setCurrency("EUR");
        return mock;
    }

    @Test
    public void test1() throws Exception {
        when(priceService.getApplicablePrice(
                eq(LocalDateTime.parse("2020-06-14T10:00:00")),
                eq(35455),
                eq(1)))
                .thenReturn(Optional.of(buildMockPrice(1, 35455, 1, 35.50)));

        mockMvc.perform(get(URL)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test2() throws Exception {
        when(priceService.getApplicablePrice(
                eq(LocalDateTime.parse("2020-06-14T16:00:00")),
                eq(35455),
                eq(1)))
                .thenReturn(Optional.of(buildMockPrice(2, 35455, 1, 25.45)));

        mockMvc.perform(get(URL)
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void test3() throws Exception {
        when(priceService.getApplicablePrice(
                eq(LocalDateTime.parse("2020-06-14T21:00:00")),
                eq(35455),
                eq(1)))
                .thenReturn(Optional.of(buildMockPrice(1, 35455, 1, 35.50)));

        mockMvc.perform(get(URL)
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test4() throws Exception {
        when(priceService.getApplicablePrice(
                eq(LocalDateTime.parse("2020-06-15T10:00:00")),
                eq(35455),
                eq(1)))
                .thenReturn(Optional.of(buildMockPrice(3, 35455, 1, 30.50)));

        mockMvc.perform(get(URL)
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void test5() throws Exception {
        when(priceService.getApplicablePrice(
                eq(LocalDateTime.parse("2020-06-16T21:00:00")),
                eq(35455),
                eq(1)))
                .thenReturn(Optional.of(buildMockPrice(4, 35455, 1, 38.95)));

        mockMvc.perform(get(URL)
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
