package com.umitclebi.msscbeerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36,columnDefinition = "varchar", updatable = false,nullable = false)
    private UUID id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdTime;

    @UpdateTimestamp
    private Timestamp lastModifiedTime;
    private String beerName;
    private String beerStyle;

    @Column(unique = true)
    private Long upc;

    private BigDecimal price;

    private Integer quantityToBrew;
    private Integer minOnHand;
}
