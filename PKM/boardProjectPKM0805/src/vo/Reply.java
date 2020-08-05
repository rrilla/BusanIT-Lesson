package vo;

import java.util.Date;

public class Reply {
	private int rno;
	private int bno;
	private String writer;
	private String replytext;
	private Date writedate;
	
	public Reply() {}
	public Reply(int bno, String writer, String replytext) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.replytext = replytext;
	}
	public Reply(int rno, int bno, String writer, String replytext) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.writer = writer;
		this.replytext = replytext;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "Reply [rno=" + rno + ", bno=" + bno + ", writer=" + writer + ", replytext=" + replytext + ", writedate="
				+ writedate + "]";
	}
}
