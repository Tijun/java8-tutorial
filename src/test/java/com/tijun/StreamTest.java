package com.tijun;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamTest {

    private List<Person> people;

    @Before
    public void initTestData(){
        people = new ArrayList<Person>();
        people.add(new Person("爱因斯坦",40,"iyst@163.com",2000));
        people.add(new Person("达芬奇",98,"dfq@163.com",1500));
        people.add(new Person("哈利波特",20,"hlbt@163.com",200));
        people.add(new Person("牛顿",50,"nd@163.com",400));
        people.add(new Person("牧云笙",20,"mys@163.com",1000));
    }

    /**
     * 惰性求值迭代优化
     */
    @Test
    public void lazyValue(){
        Stream<Float> salaryStream = people.stream().map(person -> {//无状态操作
            System.out.println("获取salary");
            return person.getSalary();
        }).map(salary->{ // 无状态操作
            System.out.println("---- slary * 2 ----- " + (salary * 2));
            return salary * 2;
        }).sorted() //有状态操作,依赖于前面执行结果，会等前面执行完毕后在排序
          .map(salary->{ //开始新的迭代
            System.out.println("-- salary * 3 --");
            return salary * 3;
        });
        System.out.println("------lazy value--------"); //先于 map，salary 表达式执行
        salaryStream.forEach(System.out::println); //中终端操作

    }

    /**
     * 筛选与切片
     */
    @Test
    public void filterLimit(){
        List<Person> personList = people.stream()
                .filter(person->{
                    System.out.println("person " + person.getName());
                    return person.getAge() >20; //选出年龄大于20的人
                })
                .limit(2) // 取两个 短路操作，迭代一次
                .collect(toList());// 静态导入
        Assert.assertEquals(personList.size(),2);
    }

    /**
     * 映射
     */
    @Test
    public void map(){
        people.stream()
                .map(Person::getEmail) //映射 获取邮箱
                .forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void sort(){
        people.stream()
                //自然排序，对象必须实现comparable接口否则异常
                // java.lang.ClassCastException: com.tijun.Person cannot be cast to java.lang.Comparable
                .sorted() //
                .forEach(System.out::println);
    }

    @Test
    public void sort2(){
       List<String> strings = Arrays.asList("a","c","b","e"); // string 实现了comparable接口
       strings.stream().sorted().forEach(System.out::println);
    }

    /**
     * 自定义排序，实现Comparator接口
     */
    @Test
    public void sort3(){
        people.stream().sorted((p1,p2)->p1.getAge()-p2.getAge()).forEach(System.out::println);
        System.out.println("---------");
        people.stream().sorted(Comparator.comparing(Person::getAge)).forEach(System.out::println);
    }

    /**
     * 匹配
     */
    @Test
    public void match(){
        boolean res = people.stream().anyMatch(person -> { //anyMatch有短路操作
            System.out.println("person " + person.getName());
            return person.getAge() > 20;
        });
        Assert.assertEquals(res,true);
        System.out.println("----------");
        boolean res1 = people.stream().allMatch(person -> { //当有条件不满足时短路
            System.out.println("person " + person.getName());
            return person.getAge() > 20;
        });
        Assert.assertEquals(res1,false);


    }

    /**
     * 查找
     */
    @Test
    public void find(){
       Optional<String> res =  people.stream().map(person -> {
            System.out.println("person " + person.getName());
            return person.getName();
            }
        ).findFirst(); //短路操作 findFirst,findAny
        System.out.println(res.get());
        System.out.println("-------");
        Optional<Person> res2 = people.stream().filter(person ->{
            System.out.println("person " + person.getName());
            return person.getAge()>20;
        }).findAny();
        System.out.println(res2.get());
    }
    /**
     * reduce
     */
    @Test
    public void reduce(){


    }
    /**
     * 收集器
     */
    @Test
    public void collector(){

    }

}
