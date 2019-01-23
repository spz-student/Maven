package com.bdqn.spz.springbootmaven.pojo;


/**
 * grade(年级表)实体类
 * Tue Nov 14 17:26:06 CST 2017 施鹏振
 */ 
public class Grade{

	//年纪编号
	private Integer gradeId;

	//年纪名称
	private String gradeName;

	/**
	 * 年纪编号
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId){
		this.gradeId=gradeId;
	}

	/**
	 * 年纪编号
	 * @return Integer gradeId
	 */
	public Integer getGradeId(){
		return gradeId;
	}

	/**
	 * 年纪名称
	 * @param gradeName
	 */
	public void setGradeName(String gradeName){
		this.gradeName=gradeName;
	}

	/**
	 * 年纪名称
	 * @return String gradeName
	 */
	public String getGradeName(){
		return gradeName;
	}
}



