package com.bootdo.User.dao;

import com.bootdo.User.domain.UserN;
import com.bootdo.UserManage.domain.UserJBT;

import java.util.List;
import java.util.Map;


/**
 * 用户Dao接口
 * @author Administrator
 *
 */
public interface UserNDao {

	/**
	 * 用户登录
	 * @param userN
	 * @return
	 */
	public UserN login(UserN userN);

	/**
	 * 查询用户
	 * @param map
	 * @return
	 */
	public List<UserN> findUser(Map<String,Object> map);

	/**
	 * 获取用户记录数
	 * @param map
	 * @return
	 */
	public Long getTotalUser(Map<String,Object> map);

	/**
	 * 更新用户
	 * @param userN
	 * @return
	 */
	public int updateUser(UserN userN);

	/**
	 * 添加用户
	 * @param userN
	 * @return
	 */
	public int addUser(UserN userN);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(Integer id);




	/*
	 *根据条件查询用户信息
	 *
	 */
	List<UserJBT> findUserByCondition(Map<String,Object> map);


	/*
	 *根据条件查询用户信息条数
	 *
	 */
	Long findUserByConditionCount(Map<String,Object> map);
}

