package com.dist.annotation;

import java.lang.annotation.*;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 16:21
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation {
    String value() default "";
}
