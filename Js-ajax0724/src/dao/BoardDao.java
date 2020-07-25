package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Board;

public class BoardDao {
	private BoardDao() {
	}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	private Connection getDBCP() {
		Connection conn=null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracleDB");
			conn = ds.getConnection();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	
	public List<Board> selectAll() {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from board";

		try {
			conn = getDBCP();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setWriter(rs.getString("writer"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWrite_date(rs.getDate("write_date"));
				list.add(board);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public Board selectOne(int bno) {
		Board board=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from board where bno=?";
		try {
			conn=getDBCP();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs=ps.executeQuery();
			if(rs.next()) {
				board=new Board();
				board.setBno(rs.getInt("bno"));
				board.setWriter(rs.getNString("writer"));
				board.setTitle(rs.getNString("title"));
				board.setContent(rs.getNString("content"));
				board.setWrite_date(rs.getDate("write_date"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return board;
	}
	
	public boolean insertBoard(Board board) {
		boolean flag=false;
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="insert into board(bno, writer, title, content) "
				+ "values(board_seq.nextval,?,?,?)";
		try {
			conn=getDBCP();
			ps=conn.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3,board.getContent());
			int n=ps.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("데이터 입력 성공");
			}else {
				System.out.println("데이터 입력 실패");
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public void delete(int bno) {
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="delete from board where bno=?";
		
		try {
			conn=getDBCP();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bno);
			int n=ps.executeUpdate();
			if(n==1) {
				System.out.println("데이터 삭제 성공");
			}else {
				System.out.println("데이터 삭제 실패");
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Board board) {
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="update board set title=?,writer=?, content=? where bno=?";
		try {
			conn=getDBCP();
			ps=conn.prepareStatement(sql);
			ps.setString(1,board.getTitle());
			ps.setString(2,board.getWriter());
			ps.setString(3, board.getContent());
			ps.setInt(4, board.getBno());
			int n=ps.executeUpdate();
			if(n==1) {
				System.out.println("데이터 업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
