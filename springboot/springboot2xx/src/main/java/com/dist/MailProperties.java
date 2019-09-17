package com.dist;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-16 17:35
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

import com.sun.istack.internal.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//必须搭配会用 下面的 这个注解进行使用
@PropertySource("classpath:config/mail.properties")
//指定在某一个环境下面进行配置的加载
@Profile("dev")
@ConfigurationProperties(  prefix = "mail")
public class MailProperties {
    @NotNull //可以使用 jsr 进行数据 的 校验
    private String host;
    private int port;
    private String from;
    private String username;
    private String password;
    private Smtp smtp;

    // ... getters and setters
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Smtp getSmtp() {
        return smtp;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }

    @Override
    public String toString() {
        return "MailProperties [host=" + host + ", port=" + port + ", from=" + from + ", username=" + username
                + ", password=" + password + ", smtp=" + smtp + "]";
    }

    public static class Smtp {
        private boolean auth;
        private boolean starttlsEnable;

        public boolean isAuth() {
            return auth;
        }

        public void setAuth(boolean auth) {
            this.auth = auth;
        }

        public boolean isStarttlsEnable() {
            return starttlsEnable;
        }

        public void setStarttlsEnable(boolean starttlsEnable) {
            this.starttlsEnable = starttlsEnable;
        }

    }
}