package com.dist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-22 14:40
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：使用这个 配置 可以 对 配置进行 运行时 的 修改 不会 去修改了 配置文件 之后 然后重启容器了
 */
@Configuration
public class JmxAutoConfig {

    @Value("${jmx.rmi.host}")
    private String rmiHost;
    @Value("${jmx.rmi.port}")
    private int rmiPort;
    @Value("${jmx.rmi.service.domain}")
    private String jmxDomain;

    // 指定特定端口可以开放命名服务
    @Bean("rmiRegistry")
    public RmiRegistryFactoryBean rmiRegistry() {
        RmiRegistryFactoryBean factoryBean = new RmiRegistryFactoryBean();
        factoryBean.setPort(rmiPort);
        factoryBean.setAlwaysCreate(true);
        return factoryBean;
    }

    @DependsOn("rmiRegistry")
    @Bean
    public ConnectorServerFactoryBean jmxConnector() {
        ConnectorServerFactoryBean serverFactoryBean = new ConnectorServerFactoryBean();
        serverFactoryBean.setServiceUrl(String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/%s", rmiHost, rmiPort, rmiHost, rmiPort, jmxDomain));
        return serverFactoryBean;
    }

    //访问 的 地址 service:jmx:rmi://127.0.0.1:7077/jndi/rmi://127.0.0.1:7077/jmxrmi
}
