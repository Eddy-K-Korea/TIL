package bit.com.spring.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import bit.com.spring.util.CalendarUtil;

//투표 작성자가 투표를 만들고 넘겨준 경우, 받은 데이터들을 설정해 줄 수 있는 그릇	polldto + pollsubdto
public class PollBean implements Serializable {

	private String id;	//작성자
	
	//시작일
	private int syear;
	private int smonth;
	private int sday;
	
	private Date sdate;	//묶어서 관리하기 위한 용도
	
	//종료일
	private int eyear;
	private int emonth;
	private int eday;
	
	private Date edate;
	
	private String question;	//질의
	private int itemcount;		//선택 보기의 개수
	
	//보기에 대한 변수
	private String poll1;
	private String poll2;
	private String poll3;
	private String poll4;
	private String poll5;
	private String poll6;
	private String poll7;
	private String poll8;
	private String poll9;
	private String poll10;
	
	private String pollnum[] = new String[10];
	
	//보기의 묶음
	public String[] getPollnum() {
		pollnum[0] = poll1;
		pollnum[1] = poll2;
		pollnum[2] = poll3;
		pollnum[3] = poll4;
		pollnum[4] = poll5;
		pollnum[5] = poll6;
		pollnum[6] = poll7;
		pollnum[7] = poll8;
		pollnum[8] = poll9;
		pollnum[9] = poll10;
		
		return pollnum;
	}
	
	//날짜의 묶음
	
	public Date getSdate() {
		return CalendarUtil.toDate(getSyear(), getSmonth(), getSday());	//캘린더 유틸에서 todate함수를 가져옴  Date 형태로 return 됨
	}
	/*
	 CalendarUtil.toDate 
	 
	2020-09-10 -> java.lang.Date로 변경
	
	public static Date toDate(int year, int month, int day) {
		String s = year + "-" + two(month + "") + "-" + two(day + "");
		Date d = Date.valueOf(s);
		return d;
	} 
	 
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	public Date getEdate() {
		return CalendarUtil.toDate(getEyear(), getEmonth(), getEday());
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	
	
	
	public PollBean() {
	}

	public PollBean(String id, int syear, int smonth, int sday, Date sdate, int eyear, int emonth, int eday, Date edate,
			String question, int itemcount, String poll1, String poll2, String poll3, String poll4, String poll5,
			String poll6, String poll7, String poll8, String poll9, String poll10, String[] pollnum) {
		super();
		this.id = id;
		this.syear = syear;
		this.smonth = smonth;
		this.sday = sday;
		this.sdate = sdate;
		this.eyear = eyear;
		this.emonth = emonth;
		this.eday = eday;
		this.edate = edate;
		this.question = question;
		this.itemcount = itemcount;
		this.poll1 = poll1;
		this.poll2 = poll2;
		this.poll3 = poll3;
		this.poll4 = poll4;
		this.poll5 = poll5;
		this.poll6 = poll6;
		this.poll7 = poll7;
		this.poll8 = poll8;
		this.poll9 = poll9;
		this.poll10 = poll10;
		this.pollnum = pollnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
	}

	public int getSmonth() {
		return smonth;
	}

	public void setSmonth(int smonth) {
		this.smonth = smonth;
	}

	public int getSday() {
		return sday;
	}

	public void setSday(int sday) {
		this.sday = sday;
	}

	public int getEyear() {
		return eyear;
	}

	public void setEyear(int eyear) {
		this.eyear = eyear;
	}

	public int getEmonth() {
		return emonth;
	}

	public void setEmonth(int emonth) {
		this.emonth = emonth;
	}

	public int getEday() {
		return eday;
	}

	public void setEday(int eday) {
		this.eday = eday;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getItemcount() {
		return itemcount;
	}

	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}

	public String getPoll1() {
		return poll1;
	}

	public void setPoll1(String poll1) {
		this.poll1 = poll1;
	}

	public String getPoll2() {
		return poll2;
	}

	public void setPoll2(String poll2) {
		this.poll2 = poll2;
	}

	public String getPoll3() {
		return poll3;
	}

	public void setPoll3(String poll3) {
		this.poll3 = poll3;
	}

	public String getPoll4() {
		return poll4;
	}

	public void setPoll4(String poll4) {
		this.poll4 = poll4;
	}

	public String getPoll5() {
		return poll5;
	}

	public void setPoll5(String poll5) {
		this.poll5 = poll5;
	}

	public String getPoll6() {
		return poll6;
	}

	public void setPoll6(String poll6) {
		this.poll6 = poll6;
	}

	public String getPoll7() {
		return poll7;
	}

	public void setPoll7(String poll7) {
		this.poll7 = poll7;
	}

	public String getPoll8() {
		return poll8;
	}

	public void setPoll8(String poll8) {
		this.poll8 = poll8;
	}

	public String getPoll9() {
		return poll9;
	}

	public void setPoll9(String poll9) {
		this.poll9 = poll9;
	}

	public String getPoll10() {
		return poll10;
	}

	public void setPoll10(String poll10) {
		this.poll10 = poll10;
	}

	@Override
	public String toString() {
		return "PollBean [id=" + id + ", syear=" + syear + ", smonth=" + smonth + ", sday=" + sday + ", sdate=" + sdate
				+ ", eyear=" + eyear + ", emonth=" + emonth + ", eday=" + eday + ", edate=" + edate + ", question="
				+ question + ", itemcount=" + itemcount + ", poll1=" + poll1 + ", poll2=" + poll2 + ", poll3=" + poll3
				+ ", poll4=" + poll4 + ", poll5=" + poll5 + ", poll6=" + poll6 + ", poll7=" + poll7 + ", poll8=" + poll8
				+ ", poll9=" + poll9 + ", poll10=" + poll10 + ", pollnum=" + Arrays.toString(pollnum) + "]";
	}
	
}
