/**
 * 
 */
package com.bdqn.spz.springbootmaven.annotate;

import scala.languageFeature.higherKinds;

/**
 * @描述 springboot-maven
 * @作者 施鹏振
 * @创建日期 2018年4月6日
 * @创建时间 上午11:48:01
 */
public class QuicklyWay {

	//当只想给value赋值时,可以使用以下快捷方式
    @IntegerVaule(20)
    public int age;

    //当name也需要赋值时必须采用key=value的方式赋值
    @IntegerVaule(value = 10000,name = "MONEY")
    public int money;

}
