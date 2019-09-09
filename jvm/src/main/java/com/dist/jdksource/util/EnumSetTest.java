package com.dist.jdksource.util;

import java.util.*;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-02 13:34
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：测试使用 set集合容器
 */


enum Day {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    private int value;


    // 私有构造
    private Day(int value) {this.value = value; }

    // 重写toString方法
    @Override
    public String toString() {
        switch(this) {
            case FRIDAY:
                return "Friday: " + value;
            case MONDAY:
                return "Monday: " + value;
            case SATURDAY:
                return "Saturday: " + value;
            case SUNDAY:
                return "Sunday: " + value;
            case THURSDAY:
                return "Thursday: " + value;
            case TUESDAY:
                return "Tuesday: " + value;
            case WEDNESDAY:
                return "Wednesday: " + value;
            default:
                return null;
        }
    }
}

enum Car {
    AUDI {
        @Override
        public int getPrice() {
            return 25000;
        }
    },
    MERCEDES {
        @Override
        public int getPrice() {
            return 30000;
        }
    },
    BMW {
        @Override
        public int getPrice() {
            return 20000;
        }
    };

    public abstract int getPrice();
}

class EnumMapExample {
    public static void main(String[] args) {
        // Create an EnumMap that contains all constants of the Car enum.
        EnumMap cars = new EnumMap(Car.class);

        // Put some values in the EnumMap.
        cars.put(Car.BMW, Car.BMW.getPrice());
        cars.put(Car.AUDI, Car.AUDI.getPrice());
        cars.put(Car.MERCEDES, Car.MERCEDES.getPrice());

        // Print the values of an EnumMap.
        for(Object c: cars.keySet())
            System.out.println(((Car)c).name());

        System.out.println(cars.size());

        // Remove a Day object.
        cars.remove(Car.BMW);
        System.out.println("After removing Car.BMW, size: " + cars.size());

        // Insert a Day object.
        cars.put(Car.valueOf("BMW"), Car.BMW.getPrice());
        cars.put(Car.valueOf("BMW"), Car.BMW.getPrice());
        System.out.println("Size is now: " + cars.size());
    }
}

public class EnumSetTest {

    public static void main(String[] args) {
        // Create an EnumSet that contains all days of the week.
        EnumSet week = EnumSet.allOf(Day.class);
        // Print the values of an EnumSet.
        for(Object d: week)
            System.out.println(((Day)d).name());

        System.out.println(week.size());

        // Remove a Day object.
        week.remove(Day.FRIDAY);
        System.out.println("After removing Day.FRIDAY, size: " + week.size());

        // Insert a Day object.
        week.add(Day.valueOf("FRIDAY"));
        System.out.println("Size is now: " + week.size());
    }

}
