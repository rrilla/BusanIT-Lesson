package vo;

import java.util.Date;

public class MemberVO {
	private int mno;
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String job;
	private String intro;
	private Date join_date;
	
	public MemberVO() {}
	
	public MemberVO(String id, String pw, String name, String gender, String job, String intro) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.job = job;
		this.intro = intro;
	}
	
	public MemberVO(int mno, String id, String pw, String name, String gender, String job, String intro,
			Date join_date) {
		super();
		this.mno = mno;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.job = job;
		this.intro = intro;
		this.join_date = join_date;
	}

	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", job="
				+ job + ", intro=" + intro + ", join_date=" + join_date + "]";
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	
	
	
}