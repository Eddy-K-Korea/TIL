package bit.com.spring.dto;

import java.io.Serializable;
import java.util.Date;

/*
 DROP TABLE POLL
CASCADE CONSTRAINTS;

DROP SEQUENCE POLL_SEQ;

CREATE TABLE POLL(
	POLLID NUMBER NOT NULL,
	ID VARCHAR2(50) NOT NULL,
	QUESTION VARCHAR2(1000) NOT NULL,
	SDATE DATE NOT NULL,
	EDATE DATE NOT NULL,
	ITEMCOUNT NUMBER NOT NULL,
	POLLTOTAL NUMBER NOT NULL,
	REGDATE DATE NOT NULL,
	CONSTRAINT POLL_PK PRIMARY KEY(POLLID)
);


CREATE SEQUENCE POLL_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE POLL ADD CONSTRAINT POLL_FK
FOREIGN KEY(ID) REFERENCES MEMBER(ID);
*/

//투표 질의
public class PollDto implements Serializable {
	
	private int pollid; // 투표번호 sequence (고유번호)
	private String id;	// 투표만든 사람(현재는 admin만 생성할 수 있도록 설정)
	private String question; //투표 질의 (ex: 좋아하는 과일은?)
	private Date sdate;	//투표 시작일
	private Date edate;	//투표 종료일
	private int itemcount; //절의에 대한 보기갯수 (ex: 사과, 바나나, 배 .... )
	private int polltotal;	// 투표 질의의 투표한 사람 수
	private Date regdate;	// 투표 만든 날
	
	private boolean vote; //이 투표 항목에 투표를 했는지 안했는지 파악하기 위한 변수

	
	public PollDto() {
	}
	

	public PollDto(int pollid, String id, String question, Date sdate, Date edate, int itemcount, int polltotal,
			Date regdate, boolean vote) {
		super();
		this.pollid = pollid;
		this.id = id;
		this.question = question;
		this.sdate = sdate;
		this.edate = edate;
		this.itemcount = itemcount;
		this.polltotal = polltotal;
		this.regdate = regdate;
		this.vote = vote;
	}


	public PollDto(String id, String question, Date sdate, Date edate, int itemcount, int polltotal) {
		super();
		this.id = id;
		this.question = question;
		this.sdate = sdate;
		this.edate = edate;
		this.itemcount = itemcount;
		this.polltotal = polltotal;
	}

	public int getPollid() {
		return pollid;
	}


	public void setPollid(int pollid) {
		this.pollid = pollid;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public Date getSdate() {
		return sdate;
	}


	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}


	public Date getEdate() {
		return edate;
	}


	public void setEdate(Date edate) {
		this.edate = edate;
	}


	public int getItemcount() {
		return itemcount;
	}


	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}


	public int getPolltotal() {
		return polltotal;
	}


	public void setPolltotal(int polltotal) {
		this.polltotal = polltotal;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public boolean isVote() {	//자동 완성에서 v가 첫글자이면 get이 아니라 is로 생성된다
		return vote;
	}


	public void setVote(boolean vote) {
		this.vote = vote;
	}


	@Override
	public String toString() {
		return "PollDto [pollid=" + pollid + ", id=" + id + ", question=" + question + ", sdate=" + sdate + ", edate="
				+ edate + ", itemcount=" + itemcount + ", polltotal=" + polltotal + ", regdate=" + regdate + ", vote="
				+ vote + "]";
	}
}
