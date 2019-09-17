package com.dist;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-10 16:35
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class App {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();
    }
}
