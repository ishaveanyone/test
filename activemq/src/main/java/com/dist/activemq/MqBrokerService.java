/**
 * Date: 2020-08-10 11:26
 * Author: xupp
 */

package com.dist.activemq;

import org.apache.activemq.broker.BrokerService;

public class MqBrokerService {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService=new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
