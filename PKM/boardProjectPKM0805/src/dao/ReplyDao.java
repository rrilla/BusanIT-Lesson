package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Reply;

public class ReplyDao {
	private ReplyDao() {}
	private static ReplyDao instance=new ReplyDao();
	public static ReplyDao getInstance() {
		return instance;
	}
	public List<Reply> selectReply(int bno){
		List<Reply> list=new ArrayList<Reply>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String sql="select * from reply where bno=?";	
		try{
			conn=DBConn.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs=ps.executeQuery();
			while(rs.next()){
				Reply reply=new Reply();
				reply.setRno(rs.getInt("rno"));
				reply.setBno(rs.getInt("bno"));
				reply.setWriter(rs.getString("writer"));
				reply.setReplytext(rs.getString("replytext"));
				reply.setWritedate(rs.getTimestamp("writedate"));
				list.add(reply);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn,ps, rs);
		}
		return list;
	}

	public boolean insertReply(Reply reply){
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql="insert into reply(rno, bno, writer, replytext) "
				+ "values(rno_seq.nextval,?,?,?)";
		try{
			conn=DBConn.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, reply.getBno());
			ps.setString(2,reply.getWriter());
			ps.setString(3, reply.getReplytext());
			int n=ps.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("댓글 입력 성공");
			}else {
				System.out.println("댓글 입력 실패");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn,ps);
		}
		return flag;
	}
	
	public Reply selectOne(String rno){
		Reply reply=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from reply where rno=?";
		try{
			conn=DBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(rno));
			rs=pstmt.executeQuery();
			if(rs.next()){
				
				reply=new Reply();
				reply.setRno(rs.getInt("rno"));
				reply.setBno(rs.getInt("bno"));
				reply.setWriter(rs.getString("writer"));
				reply.setReplytext(rs.getString("replytext"));
				reply.setWritedate(rs.getDate("writedate"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn,pstmt);
		}
		return reply;
	}
	
	public boolean replyDelete(int rno){
		boolean flag=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from reply where rno=?";
		try{
			conn=DBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			int n=pstmt.executeUpdate();
			if(n==1) {
				flag=true;
				System.out.println("댓글삭제 성공");
			}else {
				System.out.println("댓글 삭제 실패");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn,pstmt);
		}
		return flag;
	}

}
