package com.micro.currencyconversionservice.controller;

import com.micro.currencyconversionservice.model.CurrencyConversion;
import com.micro.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    @Autowired
    CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        HashMap<String, String> uriVars = new HashMap<>();
        uriVars.put("from", from);
        uriVars.put("to", to);
        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVars);
        CurrencyConversion result = response.getBody();

        return new CurrencyConversion(result.getId(),
                from, to, quantity,
                result.getConversionMultiple(),
                quantity.multiply(result.getConversionMultiple()),
                result.getEnvironment() + " rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion result = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(result.getId(),
                from, to, quantity,
                result.getConversionMultiple(),
                quantity.multiply(result.getConversionMultiple()),
                result.getEnvironment() + " feign");
    }
}
