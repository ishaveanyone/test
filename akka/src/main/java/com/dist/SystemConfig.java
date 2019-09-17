package com.dist;

import akka.actor.ActorSystem;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-09 11:27
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class SystemConfig {

     static ActorSystem system = ActorSystem.create("test-system");
}
