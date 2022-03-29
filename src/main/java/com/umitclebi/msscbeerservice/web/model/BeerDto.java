package com.umitclebi.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BeerDto {
    private UUID id;
    private Integer version;

    private OffsetDateTime createdTime;
    private OffsetDateTime lastModifiedTime;

    private String beerName;

    private BeerStyleEnum beerStyle;

    private Long upc;

    private BigDecimal price;

    private Integer quantityOnHand;
}
