package com.bootdo.User.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bootdo.User.Service.UserNService;
import com.bootdo.User.dao.UserNDao;
import com.bootdo.User.domain.UserN;
import com.bootdo.UserManage.domain.UserJBT;
import org.springframework.stereotype.Service;


/**
 * 用户Service实现类
 * @author Administrator
 *
 */
@Service("userService")
public class UserNServiceImpl implements UserNService {

	@Resource
	private UserNDao userNDao;

	@Override
	public UserN login(UserN userN) {
		return userNDao.login(userN);
	}

	@Override
	public List<UserN> findUser(Map<String, Object> map) {
		return userNDao.findUser(map);
	}

	@Override
	public int updateUser(UserN userN) {
		return userNDao.updateUser(userN);
	}

	@Override
	public Long getTotalUser(Map<String, Object> map) {
		return userNDao.getTotalUser(map);
	}

	@Override
	public int addUser(UserN userN) {
		return userNDao.addUser(userN);
	}

	@Override
	public int deleteUser(Integer id) {
		return userNDao.deleteUser(id);
	}

	@Override
	public List<UserJBT> findUserByCondition(Map<String, Object> map) {
		return userNDao.findUserByCondition(map);
	}

	@Override
	public Long findUserByConditionCount(Map<String, Object> map) {
		return userNDao.findUserByConditionCount(map);
	}

}
