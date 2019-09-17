package com.dist;

import org.apache.bval.jsr.ApacheValidationProvider;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;
import java.util.Set;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-10 16:21
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class App {
    public static void main(String[] args) {
        ValidatorFactory validatorFactory
                = Validation.byProvider(ApacheValidationProvider.class)
                .configure().buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
      /*
        User user
                = new User("ddd", "pass", "nameTooLong", 20);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        System.out.println(violations.size());*/
//验证指定的属性值
        /*User user = new User(null, "pass", "Ana", 12);

        Set<ConstraintViolation<User>> propertyViolations
                = validator.validateProperty(user, "age");

        System.out.println(propertyViolations.size());//打印 一个 代表有一个错误*/
//验证值是否有效
        User user = new User("ana@yahoo.com", "pass", "Ana", 18);

        Set<ConstraintViolation<User>> valueViolations
                = validator.validateValue(User.class, "age", 20);

        System.out.println(valueViolations.size());
    }
}
