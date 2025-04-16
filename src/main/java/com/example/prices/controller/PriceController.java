package com.example.prices.controller;

import com.example.prices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<?> getPrice( @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId) {

        // Se llama al servicio para obtener el precio aplicable y se devuelve la respuesta correspondiente
        return priceService.getApplicablePrice(date, productId, brandId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
