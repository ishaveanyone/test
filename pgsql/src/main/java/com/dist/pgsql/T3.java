package com.dist.pgsql;

import lombok.Data;

import javax.persistence.*;
//做到同一个 用户 访问 不同 的 schema

@Table(name = "t3",schema = "x2schema")
@Entity
@Data
public class T3 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;
}
