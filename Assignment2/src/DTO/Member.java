package DTO;

public class Member {
	int memberId;
	String loginId;
	String password;
	String name;
	String email;
	
	public Member(String loginId, String password, String name, String email) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public Member(String loginId, String name, String email) {
		this.loginId = loginId;
		this.name = name;
		this.email = email;
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
