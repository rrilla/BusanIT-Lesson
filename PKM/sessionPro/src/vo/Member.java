package vo;

import java.util.Date;

public class Member {
	private int no;
	private String id;
	private String pw;
	private String name;
	private Date reg_date;
	
	public Member() {}
	
	public Member(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public Member(int no, String id, String pw, String name, Date reg_date) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.reg_date = reg_date;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", reg_date=" + reg_date + "]";
	}
}
