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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import dao.UserDao;
import entity.User;
import entity.UserVo;

@Rollback(value=false)
public class userTest {
	
	//mybatis配置文件路径常量
	public  final String resource = "mybatis.xml";
	//mybatis核心的类
	private   SqlSessionFactory sqlSessionFactory ;
	//mybatis核心的类
	private SqlSession sqlSession;
	//已经把sqlSession封装起来的dao
	private static UserDao userDao;
	
	@Before
	public void before() throws IOException{
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建sqlSession工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//得到sqlSession
		sqlSession = sqlSessionFactory.openSession();
		//创建useDao的代理对象
		userDao = sqlSession.getMapper(UserDao.class);
		
	}
	
	@After
	public void after() {
		sqlSession.commit();
		sqlSession.close();
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
	
	
	/**
	 * 查询得到userVo，关联出user
	 */
	@Test
	public void findUserVoListTest(){
		User userParam = new User();
		userParam.setUserName("h");
		List<UserVo> userVoList = userDao.findUserVoList(userParam);
		
		for (UserVo resultUserVo : userVoList) {
			System.out.println("--------------查询到userVo:id:"+resultUserVo.getId()+" name:"+resultUserVo.getName()+" pwd:"+resultUserVo.getPwd());
			System.out.println("--------------查询到user:id:"+resultUserVo.getUser().getId()+" UserName:"+resultUserVo.getUser().getUserName()+" Password:"+resultUserVo.getUser().getPassword());
		}
	}
}
