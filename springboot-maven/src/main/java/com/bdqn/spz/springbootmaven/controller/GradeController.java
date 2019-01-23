package com.bdqn.spz.springbootmaven.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdqn.spz.springbootmaven.pojo.Grade;
import com.bdqn.spz.springbootmaven.service.IGradeService;

@Controller //标明这是一个SpringMVC的Controller控制器
/*
 * 1.Controller, RestController的共同点,都是用来表示Spring某个类的是否可以接收HTTP请求
 * 2.Controller, RestController的不同点
 * 		(1)@Controller:标识一个Spring类是Spring MVC controller处理器
 *		(2)@RestController:(返回json,主要用于ajax)是@Controller和@ResponseBody的结合体，两个标注合并起来的作用。*/
public class GradeController {

	@Resource
	private IGradeService gradeService;
	
	@RequestMapping("login")
    public String hello(Model model) {
		System.out.println(gradeService==null);
		List<Grade> grades=gradeService.findGrade();
		for (Grade grade : grades) {
			System.out.println(grade.getGradeName());
		}
		model.addAttribute("grades",grades);
    	model.addAttribute("name","施鵬振");
         return "grade";
    }

    @RequestMapping("grade")
    public String home() {
        return "grade";
    }
    
}