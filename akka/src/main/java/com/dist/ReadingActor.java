package com.dist;


import akka.actor.AbstractActor;
import akka.actor.Props;

public class ReadingActor extends AbstractActor {
    private String text;

    public static Props props(String text) {
        return Props.create(ReadingActor.class, text);
    }

    public Receive createReceive() {
        return null;
    }

    public Object ReadLines() {
        return new Object();
    }
    // ...
}