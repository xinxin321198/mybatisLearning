package base;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import entity.User;
import utils.SqlSessionUtils;


public class userTest {
	
	public static final String resource = "mybatis.xml";
	
	
	/**
	 * 得到一个单个用户的示例
	 * @throws IOException
	 */
	@Test
	public void getUserTest() throws IOException{
		//通过会化工厂得到sqlSession
		
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(resource);
		
		//通过sqlSession操作数据库
		//第一个参数：statement的ID
		//第二个参数：指定映射文件中匹配的参数
		User resultUser = sqlSession.selectOne("mapper.get", 1);
		
		System.out.println("--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		
		//查询结束，关闭sqlSession
		SqlSessionUtils.closeSqlSession(sqlSession);
	}
	
	
	/**
	 * 根据name模糊查询
	 * @throws IOException
	 */
	@Test
	public void findUserListByNameTest() throws IOException{
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(resource);
		
		List<User> userList = sqlSession.selectList("mapper.findByUserName", "h");
		
		for (User resultUser : userList) {
			System.out.println("--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		}
		
		
		SqlSessionUtils.closeSqlSession(sqlSession);
	}
	
	
	/**
	 * 添加用户
	 * @throws IOException
	 */
	@Test
	public void insertUserTest() throws IOException{
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(resource);
		
		User insertUser = new User();
		insertUser.setUserName(UUID.randomUUID().toString());
		insertUser.setPassword(String.valueOf(new Random().nextInt(9999999)));
		
		sqlSession.insert("mapper.insert", insertUser);
		sqlSession.commit();
		System.out.println("-----------新增的user对象的ID是："+insertUser.getId());
		SqlSessionUtils.closeSqlSession(sqlSession);
	}
}
