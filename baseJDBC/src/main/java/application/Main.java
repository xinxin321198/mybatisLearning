package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.MySqlDB1;

/**
 * 
 * author: loserStar
 * date: 2017年3月3日下午4:33:01
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:基本的jdbc程序
 */
public class Main {

	public static void main(String[] args) {
		//基本的jdbc查询数据库
		
		System.out.println("基本的jdbc查询数据库");
		
		//得到连接对象
		Connection con;
		try {
			//创建连接,连接mysql6必须设置时区。连接mysql6，旧的驱动包已经过时
			con = MySqlDB1.getConn("jdbc:mysql://127.0.0.1:3306/dataagg?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","com.mysql.cj.jdbc.Driver");
			
			//得到查询用的statement对象
			Statement statement = MySqlDB1.getStatement(con);
			String searchSql  ="select * from sys_users";
			//执行查询
			ResultSet rs  = MySqlDB1.getResultSet(statement, searchSql);
			int col = rs.getMetaData().getColumnCount();
			System.out.println("============================");
			while(rs.next()){
				for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
			}
			System.out.println("============================");
			
			
			
			String inserSql = "insert into sys_users(user_name,password) values(?,?)";
			PreparedStatement  pstmt = con.prepareStatement(inserSql);
			pstmt.setString(1, "heheda");
			pstmt.setString(2, "mimamimamima");
			int i = pstmt.executeUpdate();
			System.out.println("insert 执行影响了"+i+"行");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

}
