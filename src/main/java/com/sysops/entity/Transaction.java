package com.sysops.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Order order;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Expert expert;
    private double amount;
    private String status;
    private Date date;
}
