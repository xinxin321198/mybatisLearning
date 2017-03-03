package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * 
 * author: loserStar
 * date: 2017年3月3日下午4:32:41
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:jdbc基本类
 */
public class MySqlDB1 {
	
	
	/**
	 * 获取数据库连接
	 * @param connectString
	 * @param jdbcDriverClass 
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 旧：jdbc:mysql://localhost/bbs?user=root&password=root 
	 * 新：jdbc:mysql://127.0.0.1:3306/dataagg?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
	 * 驱动包：
	 *  com.mysql.cj.jdbc.Driver（新） 
	 *  com.mysql.jdbc.Driver"（旧）
	 */
	public static Connection getConn(String connectString,String jdbcDriverClass) throws SQLException, ClassNotFoundException {
		//"jdbc:mysql://localhost/bbs?user=root&password=root"
		Connection conn = null;
		Class.forName(jdbcDriverClass);
		conn = DriverManager.getConnection(connectString);
		return conn;
	}
	
	/**
	 * 根据连接对象获取statement
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static Statement getStatement(Connection conn) throws Exception {
		Statement stmt = null; 
		if(conn != null) {
			stmt = conn.createStatement();
		}else{
			throw new Exception("stmt不能为null");
		}
	return stmt;
	}
	
	/**
	 * 根据statement和查询sql语句，查询数据
	 * @param stmt
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public static ResultSet getResultSet(Statement stmt, String sql) throws Exception {
		ResultSet rs = null;
		if(stmt != null) {
			rs = stmt.executeQuery(sql);
		}else{
			throw new Exception("stmt不能为null");
		}
		return rs;
	}
	
	/**
	 * 增删改
	 * @param conn
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public static int executeNoQuery(Connection conn,String sql) throws SQLException {
	    int i = 0;
	    PreparedStatement pstmt;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        i = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
	    return i;
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 * @throws Exception 
	 */
	public static void closeConn(Connection conn) throws Exception {
		if(conn != null) {
			conn.close();
		}else{
			throw new Exception("conn不能为null");
		}
	}
	
	/**
	 * 关闭statement
	 * @param stmt
	 * @throws Exception 
	 */
	public static void closeStmt(Statement stmt) throws Exception {
		if(stmt != null) {
			stmt.close();
			stmt = null;
		}else{
			throw new Exception("stmt不能为null");
		}
	}
	
	/**
	 * 关闭数据集
	 * @param rs
	 * @throws Exception 
	 */
	public static void closeRs(ResultSet rs) throws Exception {
		if(rs != null) {
			rs.close();
		}else{
			throw new Exception("rs不能为null");
		}
	}
}
