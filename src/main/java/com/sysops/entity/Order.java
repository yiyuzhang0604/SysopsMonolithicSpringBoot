package com.sysops.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {

    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Customer customer;
    private double totalAmount;
    @OneToOne
    private Address shippingAddress;
    @OneToMany(mappedBy = "order" )
    private Set<Ticket> ticket;
}
