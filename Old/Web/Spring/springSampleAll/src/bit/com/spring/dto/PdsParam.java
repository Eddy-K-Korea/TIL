package bit.com.spring.dto;

public class PdsParam {

	private String choice;
	private String searchWord;
	
	public PdsParam() {
	}

	public PdsParam(String choice, String searchWord) {
		super();
		this.choice = choice;
		this.searchWord = searchWord;
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

	@Override
	public String toString() {
		return "PdsParam [choice=" + choice + ", searchWord=" + searchWord + "]";
	}
	
}
