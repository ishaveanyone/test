package com.dist;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-09 11:32
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class ReadingActor  extends AbstractActor {

    private String text;
    public static Props props(String text) {
        return Props.create(ReadingActor.class, text);
    }
    @Override
    public Receive createReceive() {
        return null;
    }
}
