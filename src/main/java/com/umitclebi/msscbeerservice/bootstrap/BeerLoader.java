package com.umitclebi.msscbeerservice.bootstrap;

import com.umitclebi.msscbeerservice.domain.Beer;
import com.umitclebi.msscbeerservice.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeeObject();
    }

    private void loadBeeObject() {
        if(beerRepository.count()==0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(12443438438L)
                    .price(new BigDecimal("12.95"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(12443438545438L)
                    .price(new BigDecimal("12.95"))
                    .build());
        }
    }

}
