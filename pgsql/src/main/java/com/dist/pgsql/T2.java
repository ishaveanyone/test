package com.dist.pgsql;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "T2")
@Data
public class T2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;
}
