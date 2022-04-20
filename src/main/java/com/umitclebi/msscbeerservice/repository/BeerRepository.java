package com.umitclebi.msscbeerservice.repository;

import com.umitclebi.msscbeerservice.domain.Beer;
import com.umitclebi.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyleEnum,Pageable pageable);
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName,BeerStyleEnum beerStyleEnum,Pageable pageable);
    Beer findByUpc(String upc);
}
