package com.dist.Subselect;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-15 14:40
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：这个的话view 语句级写在了 数据库上面
 */
@Entity(name = "View_AA")
@Data
@ToString
public class ViewEntity {

    @Id
    private Integer id;
    @Column
    private String aage;
    @Column
    private String bage;
}
