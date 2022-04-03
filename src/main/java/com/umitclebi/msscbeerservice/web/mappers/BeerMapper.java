package com.umitclebi.msscbeerservice.web.mappers;

import com.umitclebi.msscbeerservice.domain.Beer;
import com.umitclebi.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
