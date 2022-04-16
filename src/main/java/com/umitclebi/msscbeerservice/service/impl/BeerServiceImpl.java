package com.umitclebi.msscbeerservice.service.impl;

import com.umitclebi.msscbeerservice.domain.Beer;
import com.umitclebi.msscbeerservice.repository.BeerRepository;
import com.umitclebi.msscbeerservice.service.BeerService;
import com.umitclebi.msscbeerservice.web.controller.NotFoundException;
import com.umitclebi.msscbeerservice.web.mappers.BeerMapper;
import com.umitclebi.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer updatedBeer=beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        updatedBeer.setBeerName(beerDto.getBeerName());
        updatedBeer.setBeerStyle(beerDto.getBeerStyle().name());
        updatedBeer.setUpc(beerDto.getUpc());
        updatedBeer.setPrice(beerDto.getPrice());

        return beerMapper.beerToBeerDto(beerRepository.save(updatedBeer));
    }
}
