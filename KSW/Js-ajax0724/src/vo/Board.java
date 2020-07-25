package vo;

import java.util.Date;

public class Board {
	private int bno;
	private String writer;
	private String title;
	private String content;
	private Date write_date;
	
	public Board() {}
	
	public Board(String writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public Board(int bno, String writer, String title, String content, Date write_date) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.write_date = write_date;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", write_date=" + write_date + "]";
	}
}
