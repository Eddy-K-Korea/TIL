package bit.com.spring.dto;

import java.io.Serializable;

public class BbsParam implements Serializable {

	//검색을 위한 값
	private String choice;
	private String searchWord;
	
	//paging 
	
	private int pageNumber = 0; //현재 페이지
	private int recordCountPerPage = 10;	//표현할 페이지의 글수
	
	private int start = 1;	//DB에서 row넘버로 꺼내오기 떄문에 지정된 10개를 가져오기 위해 start는 1 end 는 10을 설정 (초기설정 컨트롤에서 현재페이지에대한 계산해줌)
	private int end = 10;
	
	public BbsParam() {
	}

	

	public BbsParam(String choice, String searchWord, int pageNumber, int recordCountPerPage, int start, int end) {
		super();
		this.choice = choice;
		this.searchWord = searchWord;
		this.pageNumber = pageNumber;
		this.recordCountPerPage = recordCountPerPage;
		this.start = start;
		this.end = end;
	}



	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}



	public int getPageNumber() {
		return pageNumber;
	}



	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}



	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}



	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}



	public int getStart() {
		return start;
	}



	public void setStart(int start) {
		this.start = start;
	}



	public int getEnd() {
		return end;
	}



	public void setEnd(int end) {
		this.end = end;
	}

	
}
