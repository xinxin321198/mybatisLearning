package daoImp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.UserDao;
import entity.User;
import entity.UserVo;

public class UserDaoImp2 extends SqlSessionDaoSupport implements UserDao{

	@Override
	public User get(int i) {
		//通过它自己的方法得到sqlSession
		SqlSession sqlSession = this.getSqlSession();
		User user = sqlSession.selectOne("mapper.get", i);
		//方法结束自动closeSqlSession,所以不用我们自己关闭
		return user;
	}
	
	@Override
	public List<User> findList(User user) {
		SqlSession sqlSession = this.getSqlSession();
		List<User> userList = sqlSession.selectList("mapper.findList", user);
		return userList;
	}
	

	@Override
	public void insert(User user) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("mapper.insert", user);
		sqlSession.commit();
	}

	@Override
	public void update(User user) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.update("mapper.update", user);
		sqlSession.commit();
	}

	@Override
	public void delete(int i) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.delete("mapper.delete",i);
		sqlSession.commit();
	}

	@Override
	public List<UserVo> findUserVoList(User user) {
		SqlSession sqlSession = this.getSqlSession();
		List<UserVo> userVos = sqlSession.selectList("mapper.findUserVoList", user);
		return userVos;
	}

	@Override
	public List<UserVo> findListByIds(List<Integer> ids) {
		SqlSession sqlSession = this.getSqlSession();
		List<UserVo> userVos = sqlSession.selectList("mapper.findListByIds", ids);
		return userVos;
	}


}
