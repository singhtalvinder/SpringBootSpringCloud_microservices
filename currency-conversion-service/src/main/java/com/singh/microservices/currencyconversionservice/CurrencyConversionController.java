package com.singh.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ResponseEntity<CurrencyConversionBean> responseEntity;
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,
												@PathVariable String to, 
												@PathVariable BigDecimal quantity) {
        // create map of parameters passed in .
		Map<String, String> uriVariables = new HashMap<>();
		
		uriVariables.put("from",  from);
		uriVariables.put("to",  to);
		
		responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class,
				uriVariables);
		
		// get actual object back.
		CurrencyConversionBean response = responseEntity.getBody();
		
		return new CurrencyConversionBean(
				response.getId(), 
				from, 
				to, 
				response.getConversionMultiple(),
				quantity, 
				quantity.multiply(response.getConversionMultiple()),
				response.getPort());
				
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
												@PathVariable String to, 
												@PathVariable BigDecimal quantity) {
   		// get actual object back.
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		
		logger.info("{}", response);
		
		return new CurrencyConversionBean(
				response.getId(), 
				from, 
				to, 
				response.getConversionMultiple(),
				quantity, 
				quantity.multiply(response.getConversionMultiple()),
				response.getPort());
	}
}
