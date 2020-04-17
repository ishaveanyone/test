/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019/12/9
 * Author: chenyp@dist.com.cn
 * Desc:
 */
package com.dist.pgsql;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractProject implements Serializable {

    @Id
    protected String xmbh= UUID.randomUUID().toString();

    protected String name;

    protected Date createTime = new Date();

}