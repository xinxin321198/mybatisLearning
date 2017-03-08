package dao;

import java.util.List;

import entity.User;
import entity.UserVo;

public interface UserDao {
	
	/**
	 * 根据id得到一个user
	 * @param i
	 * @return
	 */
	public User get(int i);
	
	
	/**
	 * 根据条件查询用户
	 * @param user
	 * @return
	 */
	public List<User> findList(User user);
	
	/**
	 * 插入一个新的user记录
	 * @param user
	 */
	public void insert(User user);
	
	/**
	 * 根据id修改一个user
	 * @param user
	 */
	public void update(User user) ;
	
	/**
	 * 根据id删除一个user
	 * @param i
	 */
	public void delete(int i) ;
	
	/**
	 * 使用映射结果集
	 * @param user
	 * @return
	 */
	public List<UserVo> findUserVoList(User user);
	
	/**
	 * 根据id集合，查询包含这些id的数据
	 * @param ids
	 * @return
	 */
	public List<UserVo> findListByIds(List<Integer> ids);
		
}
