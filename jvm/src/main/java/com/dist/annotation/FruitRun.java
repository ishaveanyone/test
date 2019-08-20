package com.dist.annotation;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-16 16:38
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

import javax.jws.Oneway;
import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * 水果名称注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface FruitName {
    String value() default "";
}


/**
 * 水果颜色注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface FruitColor {

    public enum Color{ BULE,RED,GREEN};

    Color fruitColor() default Color.GREEN;
}

/**
 * 水果供应者注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface FruitProvider {
    /**
     * 供应商编号
     */
    public int id() default -1;

    /**
     * 供应商名称
     */
    public String name() default "";

    /**
     * 供应商地址
     */
    public String address() default "";
}

/***********注解使用***************/

class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor= FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id=1,name="陕西红富士集团",address="陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }
    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }
    public String getAppleName() {
        return appleName;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
    public String getAppleProvider() {
        return appleProvider;
    }

    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }
}

/***********注解处理器***************/

 class FruitInfoUtil {
    public static void getFruitInfo(Class clazz){

        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";

        Field[] fields = clazz.getDeclaredFields();

        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                System.out.println(strFruitName);
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }


    public static void getFruitInfo(Object object){

        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                //如果不是默认值就是用 自己定义的数据
                if(object instanceof Apple){
                    if(!fruitName.value().equals(((Apple)object).getAppleName())){
                        System.out.println(strFruitName+((Apple)object).getAppleName());


                    }else {
                        System.out.println(strFruitName+fruitName.value());
                    }

                }
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                if(object instanceof Apple){
                    if(!fruitColor.fruitColor().equals(((Apple)object).getAppleName())){
                        System.out.println(strFruitName+((Apple)object).getAppleColor());

                    }else {
                        System.out.println(strFruitName+fruitColor.fruitColor());

                    }

                }
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                if(object instanceof Apple){
                    if(!fruitProvider.name().equals(((Apple)object).getAppleName())){
                        System.out.println(strFruitName+((Apple)object).getAppleProvider());


                    }else {
                        System.out.println(strFruitName+fruitProvider.name());
                    }

                }
            }
        }
    }
}
public class FruitRun {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Apple apple =new Apple();
        apple.setAppleColor(FruitColor.Color.BULE.toString());
        FruitInfoUtil.getFruitInfo(apple);
    }

}
