package base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;

import dao.UserDao;
import entity.User;
import entity.UserVo;

@Rollback(value=false)
public class userTest {
	
	//mybatis核心的类
//	private   SqlSessionFactory sqlSessionFactory ;
	//mybatis核心的类
//	private SqlSession sqlSession;
	//用来注入已经把sqlSession封装起来的dao，并且提供的sqlSessionFactory的构造方法
	private static UserDao userDao;
	//用来注入集成自SqlSessionDaoSupport的的dao实现类
	private static UserDao userDao2;
	
	@Before
	public void before() throws IOException{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		//第一种：原始注入的实现
		userDao = (UserDao)applicationContext.getBean("userDao1");
		//第二种：继承spring-mybatis提供的dao类，方便一些
		userDao2 = (UserDao)applicationContext.getBean("userDao2");
	}
	
	
	
	
	/**
	 * 根据name模糊查询
	 * @throws IOException
	 */
	@Test
	public void findUserListByNameTest() throws IOException{
		User userParam = new User();
		userParam.setUserName("h");
		List<User> userList = userDao.findList(userParam);
		
		for (User resultUser : userList) {
			System.out.println("原始注入dao--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		}
		
		List<User> userList2 = userDao2.findList(userParam);
		
		for (User resultUser2 : userList2) {
			System.out.println("集成自SqlSessionDaoSupport后--------------查询到user222:id:"+resultUser2.getId()+" userName:"+resultUser2.getUserName()+" password:"+resultUser2.getPassword());
		}
		
	}
	
	
	
	
	
	
	
	/**
	 * 添加用户
	 * @throws IOException
	 */
	@Test
	public void insertUserTest() throws IOException{
		User insertUser = new User();
		insertUser.setUserName(UUID.randomUUID().toString());
		insertUser.setPassword(String.valueOf(new Random().nextInt(9999999)));
		userDao.insert(insertUser);
		System.out.println("-----------------新添加的记录的id："+insertUser.getId());
	}
	
	/**
	 * 得到一个单个用户的示例
	 * @throws IOException
	 */
	@Test
	public void getUserTest() throws IOException{
		User resultUser = userDao.get(16);
		System.out.println("--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		
	}
	
	

	
	
	/**
	 * 修改用户信息
	 * @throws IOException 
	 */
	@Test
	public void updateUserTest() throws IOException{
		User updateUser = new User();
		updateUser.setId(16);
		updateUser.setUserName("newName_"+UUID.randomUUID());
		updateUser.setPassword(String.valueOf("newPassword_"+new Random().nextInt(9999999)));
		userDao.update(updateUser);
	}
	
	
	@Test
	public void deleteUserTest() throws IOException{
		userDao.delete(16);
	}
	
	
	@Test
	public void findUserVoListTest(){
		User userParam = new User();
		userParam.setUserName("h");
		List<UserVo> userVoList = userDao.findUserVoList(userParam);
		
		for (UserVo resultUserVo : userVoList) {
			System.out.println("--------------查询到userVo:id:"+resultUserVo.getId()+" name:"+resultUserVo.getName()+" pwd:"+resultUserVo.getPwd());
		}
	}
	
	
	@Test
	public void findListByIdsTest(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(12);
		ids.add(13);
		ids.add(14);
		List<UserVo> userVoList = userDao.findListByIds(ids);
		
		for (UserVo resultUserVo : userVoList) {
			System.out.println("--------------查询到userVo:id:"+resultUserVo.getId()+" name:"+resultUserVo.getName()+" pwd:"+resultUserVo.getPwd());
		}
	}
}
