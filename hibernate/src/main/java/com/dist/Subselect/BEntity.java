package com.dist.Subselect;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-15 9:46
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Entity(name = "B")
@Data
public class BEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="bage")
    private String bage;

}