package com.sysops.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Refund {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reason;
    private double amount;
    private String status;

    @OneToOne
    private Transaction transaction;

    @OneToOne
    private Customer customer;
}
