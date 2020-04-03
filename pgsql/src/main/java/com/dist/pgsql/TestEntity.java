/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-02 11:32
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist.pgsql;

import lombok.Data;

import javax.persistence.*;

@Table(name = "test")
@Entity
@Data
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Integer id;

}
