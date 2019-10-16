package com.dist.pgsql;

import lombok.Data;

import javax.persistence.*;
@Table(name = "t1",schema = "tschema")
@Entity
@Data
public class T1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;
}
