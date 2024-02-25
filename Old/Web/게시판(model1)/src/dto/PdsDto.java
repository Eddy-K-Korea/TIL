package dto;

import java.io.Serializable;

public class PdsDto implements Serializable {
	/*
	 파일을 업로드 또는 다운로드는 파일자체를 전송하는것이 아니라 그쪽 서버나 클라이언트에 새 파일을 생성하고 데이터만 전송하여 그 데이터를 기록하거나 읽어오는 것이다. 
	 파일명이 중복되면 충돌이 일어난다.(불러올때 덮어쓰기가된것이 불러온다)
	 - 시간을 얻어옴(시스템시간 숫자로 저장하고 나중에 파일명으로 바꾼다)
	 */

	private int seq;
	private String id;
	private String title;
	private String content;
	
	private String filename;	// 경로 + 파일명
	private int readcount;
	private int downcount;
	
	private String regdate;
	
	public PdsDto() {
	}

	public PdsDto(int seq, String id, String title, String content, String filename, int readcount, int downcount,
			String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
	}

	public PdsDto(String id, String title, String content, String filename) { // 유저가 입력하는 부분의 생성자는 항상 따로 만들어 준다.
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
				+ filename + ", readcount=" + readcount + ", downcount=" + downcount + ", regdate=" + regdate + "]";
	}
	
}
