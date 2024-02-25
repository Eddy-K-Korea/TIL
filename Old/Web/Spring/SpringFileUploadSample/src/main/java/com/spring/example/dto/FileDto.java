package com.spring.example.dto;

public class FileDto {
	private int seq;
	private String originfilename;
	private String safefilename;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOriginfilename() {
		return originfilename;
	}

	public void setOriginfilename(String originfilename) {
		this.originfilename = originfilename;
	}

	public String getSafefilename() {
		return safefilename;
	}

	public void setSafefilename(String safefilename) {
		this.safefilename = safefilename;
	}

	@Override
	public String toString() {
		return "FileDto [seq=" + seq + ", originfilename=" + originfilename + ", safefilename=" + safefilename + "]";
	}

}
