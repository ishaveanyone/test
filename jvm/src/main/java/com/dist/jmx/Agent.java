/*
package com.dist.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

*/
/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-19 16:41
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 *//*


public class Agent
{
    public static void main(String[] args) throws JMException, Exception
    {

        web();
    }

    public static void jconsole() throws JMException, Exception
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);
        Thread.sleep(60*60*1000);
    }

    public static void web() throws JMException, Exception
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        ObjectName testName = new ObjectName("jmxBean:name=Test");
        //create mbean and register mbean
        server.registerMBean(new Test(), testName);

        server.registerMBean(new Hello(), helloName);
        // 下面的默认端口是8082
        ObjectName adapterName = new ObjectName("Agent:name=htmladapter");

        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.setPort(8087);//如果要修改端口 这样修改
        server.registerMBean(adapter, adapterName);
        adapter.start();
    }

}
*/
