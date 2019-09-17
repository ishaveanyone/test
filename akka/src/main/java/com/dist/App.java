package com.dist;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-09 11:29
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class App {

    public static void main(String[] args) {
        ActorSystem system=SystemConfig.system;

        ActorRef myActorRef
                = system.actorOf(Props.create(MyActor.class), "my-actor");

        ActorRef readingActorRef = system.actorOf(
                ReadingActor.props("aa"), "readingActor");


    }
}
