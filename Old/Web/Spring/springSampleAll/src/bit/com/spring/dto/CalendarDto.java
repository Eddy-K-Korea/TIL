package bit.com.spring.dto;

import java.io.Serializable;
/*
DROP TABLE CALENDAR
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_CAL;

CREATE TABLE CALENDAR(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	RDATE VARCHAR2(12) NOT NULL,	
	WDATE DATE NOT NULL	
);

CREATE SEQUENCE SEQ_CAL
START WITH 1
INCREMENT BY 1;

ALTER TABLE CALENDAR
ADD CONSTRAINT FK_CAL_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);
*/
public class CalendarDto implements Serializable {
	
	private int seq;
	private String id;
	private String title;
	private String content;
	private String rdate;	// 일정이 있는 날  20/08/03
	private String wdate;	// 작성일		  20/07/31()	
	
	public CalendarDto() {
	}

	public CalendarDto(int seq, String id, String title, String content, String rdate, String wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.rdate = rdate;
		this.wdate = wdate;
	}

	public CalendarDto(String id, String title, String content, String rdate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.rdate = rdate;
	}

	
	public CalendarDto(String id, String rdate) {
		super();
		this.id = id;
		this.rdate = rdate;
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

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	@Override
	public String toString() {
		return "CalendarDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", rdate="
				+ rdate + ", wdate=" + wdate + "]";
	}
	
	

}
