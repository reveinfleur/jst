package com.io.jst.model.entity;

import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;

@Entity
@Data
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String itemName;
    private long price;


}
