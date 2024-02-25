package dto;

import java.io.Serializable;

public class MemberDto implements Serializable {

	private String id;
	private String pwd;
	private String name;
	private String birth;
	private String sex;
	private String email;
	private int phone;
	private int auth; 	// 사용자(3) / 관리자 (1)
	
	public MemberDto() {
	}

	public MemberDto(String id, String pwd, String name, String birth, String sex, String email, int phone,
			int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.auth = auth;
	}
	
	

	public MemberDto(String id, String pwd, String name, String email, int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", birth=" + birth + ", sex=" + sex
				+ ", email=" + email + ", phone=" + phone + ", auth=" + auth + "]";
	}

}
