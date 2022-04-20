package com.umitclebi.msscbeerservice.service.impl;

import com.umitclebi.msscbeerservice.domain.Beer;
import com.umitclebi.msscbeerservice.repository.BeerRepository;
import com.umitclebi.msscbeerservice.service.BeerService;
import com.umitclebi.msscbeerservice.web.controller.NotFoundException;
import com.umitclebi.msscbeerservice.web.mappers.BeerMapper;
import com.umitclebi.msscbeerservice.web.model.BeerDto;
import com.umitclebi.msscbeerservice.web.model.BeerPagedList;
import com.umitclebi.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId,Boolean showInventoryOnHand) {
        if (showInventoryOnHand){
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }else {
            return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
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

    @Override
    public BeerPagedList lisTBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleEnum)){
            beerPage=beerRepository.findAllByBeerNameAndBeerStyle(beerName,beerStyleEnum,pageRequest);
        }else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyleEnum)){
            beerPage=beerRepository.findAllByBeerName(beerName,pageRequest);
        }else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleEnum)){
            beerPage=beerRepository.findAllByBeerStyle(beerStyleEnum,pageRequest);
        }else{
            beerPage=beerRepository.findAll(pageRequest);
        }
        if (showInventoryOnHand){
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }else {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }


        return beerPagedList;
    }
}
