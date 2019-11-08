package com.dist.pgsql;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Table(name = "t6",schema = "xupp")
@Entity
@Data
public class T6 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name="name",nullable = false)
    @Lob
    private String name;

}
