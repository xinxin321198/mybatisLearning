package utils;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	
	/**
	 * 根据mybatis配置文件，得到sqlSession
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	public static SqlSession getSqlSession(String resource) throws IOException{
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		return factory.openSession();
	}
	
	/**
	 * 关闭sqlSession
	 * @param sqlSession
	 */
	public static void closeSqlSession(SqlSession sqlSession){
		sqlSession.close();
	}
}
