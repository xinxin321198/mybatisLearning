package daoImp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.UserDao;
import entity.User;
import entity.UserVo;

public class UserDaoImp implements UserDao{

	//需要向dao注入mybatis的sqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
	
	//提供一个sqlSessionFactory的构造方法，使用构造器注入
	public UserDaoImp(SqlSessionFactory sqlSessionFactory) {
		super();
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User get(int i) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("mapper.get", i);
		sqlSession.close();
		return user;
	}
	
	@Override
	public List<User> findList(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> userList = sqlSession.selectList("mapper.findList", user);
		sqlSession.close();
		return userList;
	}
	

	@Override
	public void insert(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("mapper.insert", user);
		sqlSession.commit();
		
		sqlSession.close();
	}

	@Override
	public void update(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("mapper.update", user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void delete(int i) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("mapper.delete",i);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public List<UserVo> findUserVoList(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserVo> userVos = sqlSession.selectList("mapper.findUserVoList", user);
		sqlSession.close();
		return userVos;
	}

	@Override
	public List<UserVo> findListByIds(List<Integer> ids) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserVo> userVos = sqlSession.selectList("mapper.findListByIds", ids);
		sqlSession.close();
		return userVos;
	}


}
