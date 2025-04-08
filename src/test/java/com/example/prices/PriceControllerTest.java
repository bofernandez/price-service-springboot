package com.example.prices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String URL = "/api/prices";

    // Prueba 1: Petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA)
    @Test
    public void test1() throws Exception {
        mockMvc.perform(get(URL)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    // Prueba 2: Petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA)
    @Test
    public void test2() throws Exception {
        mockMvc.perform(get(URL)
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    // Prueba 3: Petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA)
    @Test
    public void test3() throws Exception {
        mockMvc.perform(get(URL)
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                // En esta fecha, se solapa la tarifa 1 (sin prioridad alta) ya que la tarifa 2 sólo aplica entre las 15:00 y 18:30.
                .andExpect(jsonPath("$.priceList").value(1));
    }

    // Prueba 4: Petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA)
    @Test
    public void test4() throws Exception {
        mockMvc.perform(get(URL)
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3));
    }

    // Prueba 5: Petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA)
    @Test
    public void test5() throws Exception {
        mockMvc.perform(get(URL)
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4));
    }
}
