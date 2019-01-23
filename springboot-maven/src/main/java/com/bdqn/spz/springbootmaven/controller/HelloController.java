package com.bdqn.spz.springbootmaven.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

	@RequestMapping("/dohello")
    public String hello() {
		System.out.println("111111111111111");
         return "hello";
    }
}
