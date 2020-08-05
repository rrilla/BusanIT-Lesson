package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Board;
import vo.Reply;

public class BoardDao {
	
	private BoardDao() {}
	private static BoardDao bDao=new BoardDao();
	public static BoardDao getInstance() {
		return bDao;
	}
	
	public List<Board> selectAll(){
		String sql = "select * from m_board order by bno desc";
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getTimestamp("writedate"));
				board.setImage_name(rs.getString("image_name"));
				board.setReplycount(rs.getInt("replycount"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	
	public List<Board> selectAll(int start, int end){
		String mysql_sql = "select * from m_board order by num desc limit ?,?";
		//String oracle_sql= "select * from (select /*+ INDEX(BNO_IDX) */ rownum as rn, b1.* from board b1) where rn between start and end";
		String oracle_sql2= "select * from (select b1.*, rownum rn from "
				+ "(select /*+ INDEX_DESC(M_BOARD BNO_IDX) */ "
				+ "bno,title,writer,readcount,writedate,replycount from m_board) b1 "
				+ "where rownum<=?) "
				+ "where rn>?";
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(oracle_sql2);
			pstmt.setInt(1,end);
			pstmt.setInt(2,start);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				//board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getTimestamp("writedate"));
				//board.setImage_name(rs.getNString("image_name"));
				board.setReplycount(rs.getInt("replycount"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public boolean insertBoard(Board board) {
		boolean flag=false;
		String sql = "insert into m_board(bno, title, content, writer,image_name) "
				+ "values(bno_seq.nextval, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.setNString(4, board.getImage_name());
			int n=pstmt.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("게시글이 DB에 저장되었습니다");
			}else {
				System.out.println("게시글 DB 저장 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return flag;
	}
	
	public boolean updateReadCount(int bno) {
		boolean flag=false;
		String sql = "update m_board set readcount=readcount+1 where bno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,bno);
			int n=pstmt.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("조회수 업데이터 성공");
			}else {
				System.out.println("조회수 업데이터 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return flag;
	}
	
	public Board selectOne(int bno) {
		String sql = "select * from m_board where bno = ?";
		Board board = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getTimestamp("writedate"));
				board.setImage_name(rs.getNString("image_name"));
				board.setReadcount(rs.getInt("readcount"));
				board.setReplycount(rs.getInt("replyCount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return board;
	}
	
	
	public boolean updateBoard(Board board) {
		boolean flag=false;
		String sql = "update m_board set title=?, content=?,image_name=? where bno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setNString(3, board.getImage_name());
			pstmt.setInt(4, board.getBno());
			int n=pstmt.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("글 업데이트 성공");
			}else {
				System.out.println("글 업데이트 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return flag;
	}
	
	public boolean deleteBoard(int bno) {
		boolean flag=false;
		String sql = "delete from m_board where bno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			int n=pstmt.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("글 삭제 성공");
			}else {
				System.out.println("글 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	public int getBoardCount(){
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select count(*) from m_board";
		try{
			conn=DBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(conn, pstmt, rs);
		}
		return count;
	}
	
	
	public void updateReplyCount(String bno, int n){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update board set replycount=replycount+? where bno=?";
		
		try{
			conn=DBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, n);
			pstmt.setInt(2,Integer.parseInt(bno) );
			int i=pstmt.executeUpdate();
			System.out.println(i);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn,pstmt);
		}
	}
	

}
