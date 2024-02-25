package bit.com.spring.dto;

import java.io.Serializable;
/*
DROP TABLE CALENDARPLUG
CASCADE CONSTRAINTS;


DROP SEQUENCE SEQ_CALPLUG;

CREATE TABLE CALENDARPLUG(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	SLEVEL NUMBER(8),
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	STARTDATE VARCHAR2(19) NOT NULL,
	ENDDATE VARCHAR2(19),
	WDATE DATE	NOT NULL
);


CREATE SEQUENCE SEQ_CALPLUG
START WITH 1
INCREMENT BY 1;

ALTER TABLE CALENDARPLUG
ADD CONSTRAINT FK_CALPLUG_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);


- 예제

INSERT INTO CALENDARPLUG(SEQ,ID,SLEVEL,TITLE,CONTENT,STARTDATE,ENDDATE,WDATE)
VALUES(SEQ_CALPLUG.NEXTVAL,'bbb',1,'비즈니스 약속','제품 계약 작성건','2020-09-25T12:30:00','',SYSDATE);

INSERT INTO CALENDARPLUG(SEQ,ID,SLEVEL,TITLE,CONTENT,STARTDATE,ENDDATE,WDATE)
VALUES(SEQ_CALPLUG.NEXTVAL,'bbb',1,'교육 연수','리더쉽 교육 연수 워크샵','2020-09-22T09:30:00','',SYSDATE);

INSERT INTO CALENDARPLUG(SEQ,ID,SLEVEL,TITLE,CONTENT,STARTDATE,ENDDATE,WDATE)
VALUES(SEQ_CALPLUG.NEXTVAL,'bbb',1,'국내 여행','차이나타운으로','2020-09-19T08:20:00','2020-09-21T20:10:00',SYSDATE);


*/
public class CalendarPlugDto implements Serializable {

	private int seq;
	private String id;
	private int slevel;	//중요도 1, 2, 3
	private String title;
	private String content;
	private String startdate;
	private String enddate;
	private String wdate;
	
	
	public CalendarPlugDto() {
	}


	public CalendarPlugDto(int seq, String id, int slevel, String title, String content, String startdate,
			String enddate, String wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.slevel = slevel;
		this.title = title;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wdate = wdate;
	}


	public CalendarPlugDto(String id, int slevel, String title, String content, String startdate, String enddate) {
		super();
		this.id = id;
		this.slevel = slevel;
		this.title = title;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getSlevel() {
		return slevel;
	}


	public void setSlevel(int slevel) {
		this.slevel = slevel;
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


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	@Override
	public String toString() {
		return "CalendarPlugDto [seq=" + seq + ", id=" + id + ", slevel=" + slevel + ", title=" + title + ", content="
				+ content + ", startdate=" + startdate + ", enddate=" + enddate + ", wdate=" + wdate + "]";
	}
}
