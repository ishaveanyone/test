package com.dist;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-19 13:32
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */


class SomeOtherActor extends AbstractActor {
    // Props(new DemoActor(42)) would not be safe
    ActorRef demoActor = getContext().actorOf(DemoActor.props(42), "demo");

    @Override
    public Receive createReceive() {
        return null;
    }
    // ...
}

class DemoMessagesActor extends AbstractLoggingActor {

    public static class Greeting {
        private final String from;

        public Greeting(String from) {
            this.from = from;
        }

        public String getGreeter() {
            return from;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Greeting.class,
                        g -> {
                            log().info("I was greeted by {}", g.getGreeter());
                        })
                .build();
    }
}


public  class  DemoActor extends AbstractActor {
    /**
     * Create Props for an actor of this type.
     *
     * @param magicNumber The magic number to be passed to this actor’s constructor.
     * @return a Props for creating this actor, which can then be further configured (e.g. calling
     *     `.withDispatcher()` on it)
     */
    private final Integer magicNumber;

    static Props props(Integer magicNumber) {
        // You need to specify the actual type of the returned actor
        // since Java 8 lambdas have some runtime type information erased
        return Props.create(DemoActor.class, () -> new DemoActor(magicNumber));
    }

    public DemoActor(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Integer.class,
                        i -> {
                            getSender().tell(i + magicNumber, getSelf());
                        })
                .build();
    }

}
