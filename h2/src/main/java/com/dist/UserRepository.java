package com.dist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-13 10:28
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
