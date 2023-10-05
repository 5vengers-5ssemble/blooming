package com.fivengers.blooming.config.contract;

import com.klaytn.caver.Caver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractConfig {

    private static final String BAOBAB_URL = "https://public-en-baobab.klaytn.net";

    @Bean
    public Caver caver() {
        return new Caver(BAOBAB_URL);
    }
}
