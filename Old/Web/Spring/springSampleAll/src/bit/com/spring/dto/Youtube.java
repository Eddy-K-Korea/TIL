package bit.com.spring.dto;

import java.io.Serializable;

public class Youtube implements Serializable {

	private String title;
	private String url;
	private String img;
	
	public Youtube() {
	}

	public Youtube(String title, String url, String img) {
		super();
		this.title = title;
		this.url = url;
		this.img = img;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Youtube [title=" + title + ", url=" + url + ", img=" + img + "]";
	}
}
