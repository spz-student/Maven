package com.bdqn.spz.springbootmaven.dao;

import java.util.List;

import com.bdqn.spz.springbootmaven.pojo.Grade;

/**
 * Dao层年级接口
 * @author 施鹏振
 *
 */
public interface IGradeDao {

	/**
	 * 查询所有年级信息
	 * @return Grade
	 */
	List<Grade> selectGrades();
}
