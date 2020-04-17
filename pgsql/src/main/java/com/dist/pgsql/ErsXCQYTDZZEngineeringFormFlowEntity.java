/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-13 13:22
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist.pgsql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity(name="ers_xcqytdzz_form_flow")
@Data
public class ErsXCQYTDZZEngineeringFormFlowEntity implements Serializable {

    @Id
    private String guid = uuid();

    public String uuid()
    {
        return UUID.randomUUID().toString();
    }

    //关联的角色编号
    private String usercode;

    //关联的工程编号
    private String gcbh;

    //报备状态
    private Integer bbzt;

}
