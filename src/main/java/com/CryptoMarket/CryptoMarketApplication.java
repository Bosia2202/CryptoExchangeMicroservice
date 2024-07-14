package com.CryptoMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(CryptoMarketApplication.class, args);
	}

}
