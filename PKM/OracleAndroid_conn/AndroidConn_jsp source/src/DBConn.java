import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConn {
	private DBConn() {}
	private static DBConn instance=new DBConn();
	public static DBConn getInstance() {
		return instance;
	}
	
	private Connection getConn() {
		Connection conn=null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/androidDB");
			conn = ds.getConnection();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public String insert(String id, String pw) {
		String str="";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from usertbl where id=?";
		String sql2="insert into usertbl(id,pw) values(?,?)";
		
		try {
			conn=getConn();
			ps=conn.prepareStatement(sql);
			ps.setNString(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				str="이미 존재하는 아이디입니다";
			}else {
				ps=conn.prepareStatement(sql2);
				ps.setNString(1,id);
				ps.setNString(2, pw);
				int n=ps.executeUpdate();
				if(n==1) {
					str="회원가입 성공";
				}else {
					str="회원가입 실패";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return str;
	}

}










