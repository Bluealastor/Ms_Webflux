package it.Alastor.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false, unique = true)
    private String taxCode;

    @Column(nullable = false)
    private Date dateTimeStart;

    @Column(nullable = false)
    private Date dateTimeEnd;

}
