package com.dist.pgsql;

import lombok.Data;

import javax.persistence.*;
//一旦指定了 默认的 schema 即使是 public 在代码层面也要手动指定 模式 名
@Table(name="t",schema = "public")
@Entity
@Data
public class T {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;
}
