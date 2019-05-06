package com.singh.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//import com.singh.microservices.currencyexchangeservice.ExchangeValue;

// name = name of service we need to call.
// url = where the service is running. NOt needed when using Ribbon. 
// Need to configure it differently.
// configure in application.properties.
//@FeignClient(name="currency-exchange-service", url="localhost:8000")

// Do not connect to this service, instead connect it via Zuul API.
// @FeignClient(name="currency-exchange-service")

@FeignClient(name="netflix-zuul-api-gateway-server")

// Ribbon -for client side load balancing.
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	// define method to talk to currency-exchange-service.
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	// prefix the servie since we are using the Zuul api server now.
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from,
			                                   @PathVariable String to);
	// Older version of feign will result in error so we need to add the actual variable name
	//  after @PathVariable.
	//public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
    //        @PathVariable("to") String to);
	

}
