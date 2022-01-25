package com.micro.currencyexchangeservice.controller;

import com.micro.currencyexchangeservice.model.CurrencyExchange;
import com.micro.currencyexchangeservice.repos.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExchangeController {
    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchange(
            @PathVariable String from,
            @PathVariable String to
    ) {

//        CurrencyExchange exchange = new CurrencyExchange(10001L,
//                "USD",
//                "VND",
//                BigDecimal.valueOf(23000L));

        CurrencyExchange exchange = repository.findByFromAndTo(from, to);
        if (exchange == null) {
            throw new RuntimeException("Unable to Find data for " + from + " : " + to);
        }

        exchange.setEnvironment(environment.getProperty("local.server.port"));

        return exchange;
    }
}
