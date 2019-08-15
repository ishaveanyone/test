package com.dist.Subselect;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-15 9:51
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Data
@Entity(name = "VIEW_COMBINE")
//可以不对应视图进行数据查询
@Subselect("select row_number() OVER (ORDER BY A.aage) AS id,  A.aage aage,B.bage bage from A join B on A.id=B.id")
@Synchronize({ "A", "B" })
@ToString
public class CombineEntity {
    @Id
    private Integer id;
    @Column
    private String aage;
    @Column
    private String bage;

}
