package com.dist.pgsql;

import lombok.Data;

import javax.persistence.*;
@Table(name = "t2",schema = "xschema")
@Entity
@Data
public class T2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;
}
