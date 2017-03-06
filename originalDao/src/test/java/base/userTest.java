package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import dao.UserDao;
import daoImp.UserDaoImp;
import entity.User;

@Rollback(value=false)
public class userTest {
	
	//mybatis配置文件路径常量
	public static final String resource = "mybatis.xml";
	//mybatis核心的类
	private static  SqlSessionFactory sqlSessionFactory ;
	//已经把sqlSession封装起来的dao
	private static UserDao userDao;
	
//	@Before
	public void before() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		userDao = new UserDaoImp(sqlSessionFactory);
	}
	
	/**
	 * 得到一个单个用户的示例
	 * @throws IOException
	 */
	@Test
	public void getUserTest() throws IOException{
//		User resultUser = userDao.get(4);
		User resultUser = new User();
		resultUser.setId(123123);
		resultUser.setUserName("123123123");
		resultUser.setPassword("123123123");
		System.out.println("--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		
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
			System.out.println("--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
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
	}
	
	
	/**
	 * 修改用户信息
	 * @throws IOException 
	 */
//	@Test
	public void updateUserTest() throws IOException{
		User updateUser = new User();
		updateUser.setId(1);
		updateUser.setUserName("newName_"+UUID.randomUUID());
		updateUser.setPassword(String.valueOf("newPassword_"+new Random().nextInt(9999999)));
		userDao.update(updateUser);
	}
	
	
//	@Test
	public void deleteUserTest() throws IOException{
		userDao.delete(1);
	}
}
