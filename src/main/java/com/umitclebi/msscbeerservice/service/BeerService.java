package com.umitclebi.msscbeerservice.service;

import com.umitclebi.msscbeerservice.web.model.BeerDto;
import com.umitclebi.msscbeerservice.web.model.BeerPagedList;
import com.umitclebi.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId,Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList lisTBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand);
}
