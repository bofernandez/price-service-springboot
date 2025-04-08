package com.example.prices.service;

import com.example.prices.model.Price;
import com.example.prices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> getApplicablePrice(LocalDateTime date, int productId, int brandId) {
        // Se recuperan las tarifas aplicables y se devuelve la primera (mayor prioridad)
        return priceRepository.findApplicablePrices(date, productId, brandId).stream().findFirst();
    }
}
