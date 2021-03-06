package com.umitclebi.msscbeerservice.web.controller;

import com.umitclebi.msscbeerservice.service.BeerService;
import com.umitclebi.msscbeerservice.web.model.BeerDto;
import com.umitclebi.msscbeerservice.web.model.BeerPagedList;
import com.umitclebi.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BeerController {

    private final BeerService beerService;

    public static final Integer DEFAULT_PAGE_SIZE=25;
    public static final Integer DEFAULT_PAGE_NUMBER=0;
    
    @GetMapping(produces = {"application/json"},path = "beer")
    public ResponseEntity<BeerPagedList> listBeer(@RequestParam(value = "pageNumber",required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                                  @RequestParam(value = "beerName",required = false) String beerName,
                                                  @RequestParam(value = "beerStyle",required = false) BeerStyleEnum beerStyleEnum,
                                                  @RequestParam(value = "showInventoryOnHand",required = false) Boolean showInventoryOnHand){
        if (showInventoryOnHand==null)
            showInventoryOnHand=false;

        if(pageNumber==null || pageNumber<0)
            pageNumber=DEFAULT_PAGE_NUMBER;

        if (pageSize==null || pageSize < 1)
            pageSize=DEFAULT_PAGE_SIZE;

        BeerPagedList beerPagedList=beerService.lisTBeers(beerName,beerStyleEnum, PageRequest.of(pageNumber,pageSize),showInventoryOnHand);
        return new ResponseEntity<>(beerPagedList,HttpStatus.OK);
    }

    @GetMapping(path = "beerUpc/{upc}", produces = {"application/json"})
    public ResponseEntity<BeerDto> listBeerByUpc(@PathVariable(value = "upc") String upc) {

        return new ResponseEntity<>(beerService.getByUpc(upc),HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId")UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand",required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand==null)
            showInventoryOnHand=false;

        return new ResponseEntity<>(beerService.getById(beerId,showInventoryOnHand),HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto){

        return new ResponseEntity(beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){

        return new ResponseEntity(beerService.updateBeer(beerId,beerDto),HttpStatus.NO_CONTENT);
    }


}
