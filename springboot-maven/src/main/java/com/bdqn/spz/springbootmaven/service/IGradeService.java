package com.bdqn.spz.springbootmaven.service;

import java.util.List;

import com.bdqn.spz.springbootmaven.pojo.Grade;

/**
 *  Service层年级接口
 * @author 施鹏振
 *
 */
public interface IGradeService {

	List<Grade> findGrade();
}
