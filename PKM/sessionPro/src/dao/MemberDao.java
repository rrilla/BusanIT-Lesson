package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.PreparedStatement;

import vo.Member;



public class MemberDao {
	private MemberDao() {}
	private static MemberDao instance=new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection getConn() {
		Connection conn=null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/jsp_oracle");
			conn = ds.getConnection();
			System.out.println("db 연결 성공");
		}catch(Exception ex) {
			ex.getStackTrace();
		}
		
		return conn;
	}
	
	public List<Member> selectAll(){
		List<Member> list=new ArrayList<Member>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from member1";
		try {
			conn=getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Member member=new Member();
				member.setNo(rs.getInt("no"));
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setReg_date(rs.getDate("reg_date"));
				list.add(member);
			}
		}catch(Exception ex) {
			ex.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {}
		}
		return list;
	}
	
	public int login(String id, String pw) {
		int n=-1;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select pw from member1 where id=?";
		try {
			conn=getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				if(pw.equals(rs.getString(1))) {
					n=1;
				}else {
					n=0;
				}
			}
		}catch(Exception ex) {
			ex.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {}
		}
		
		return n;
	}
	
	
	
}
