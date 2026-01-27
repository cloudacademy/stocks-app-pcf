package com.cloudacademy.stocks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private Date date;

    @Column(nullable = false)
    private Double open;

    @Column(nullable = false)
    private Double high;

    @Column(nullable = false)
    private Double low;

    @Column(nullable = false)
    private Double close;

    @Column(nullable = false)
    private Integer volume;
}
