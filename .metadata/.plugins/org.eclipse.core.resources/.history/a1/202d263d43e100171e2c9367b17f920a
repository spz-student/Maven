package com.struts2.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IteratorAction extends ActionSupport {
	private List<String> list;
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	/**
	 * execute方法，当Struts2处理用户请求时，在默认配置下调用的方法。
	 * @return
	 */
	public String execute() {
		list=new ArrayList<String>();
		list.add("Jack");
		list.add("Marry");
		list.add("John");
		list.add("Bob");
		return SUCCESS;
	}
}
