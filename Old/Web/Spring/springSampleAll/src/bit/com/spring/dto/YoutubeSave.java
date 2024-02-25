package bit.com.spring.dto;

import java.io.Serializable;
/*
 DROP TABLE YOUTUBE
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_YOUTUBE;

CREATE TABLE YOUTUBE(
	SEQ NUMBER PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	URL VARCHAR2(100) NOT NULL,
	WDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_YOUTUBE
START WITH 1
INCREMENT BY 1

ALTER TABLE YOUTUBE 
ADD CONSTRAINT FK_YOUTUBE FOREIGN KEY (ID) REFERENCES MEMBER(ID); 
  
 
 */
public class YoutubeSave implements Serializable {

	private int seq;
	private String id;
	private String title;
	private String url;
	private String wdate;
	
	public YoutubeSave() {
	}

	public YoutubeSave(int seq, String id, String title, String url, String wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.url = url;
		this.wdate = wdate;
	}

	public YoutubeSave(String id, String title, String url) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	@Override
	public String toString() {
		return "YoutubeSave [seq=" + seq + ", id=" + id + ", title=" + title + ", url=" + url + ", wdate=" + wdate
				+ "]";
	}
}
