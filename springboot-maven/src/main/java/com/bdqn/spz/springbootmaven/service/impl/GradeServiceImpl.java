package com.bdqn.spz.springbootmaven.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdqn.spz.springbootmaven.dao.IGradeDao;
import com.bdqn.spz.springbootmaven.pojo.Grade;
import com.bdqn.spz.springbootmaven.service.IGradeService;

/**
 * Service层年级现实类
 * @author 施鹏振
 *
 */
@Service
public class GradeServiceImpl implements IGradeService {

	@Resource
	private IGradeDao gradeDao;
	
	@Transactional
	@Override
	public List<Grade> findGrade() {
		return gradeDao.selectGrades();
	}

}
