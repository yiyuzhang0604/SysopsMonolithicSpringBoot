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
public class Bill {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Customer customer;

    @OneToOne
    private Expert expert;

    @OneToOne
    private Order order;

    private String paymentMethod;

    private double paymentAmount;

    private String paymentStatus;

    private Date paymentDate;

}
