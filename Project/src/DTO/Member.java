package DTO;

public class Member {
	int memberId;
	String loginId;
	String password;
	String name;
	String department;
	
	public Member(int memberId, String loginId, String password, String name, String department) {
		this.memberId = memberId;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.department = department;
	}

	public Member(String loginId, String password, String name, String department) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.department = department;
	}
	
	public Member(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}
	
	public Member(String loginId) {
		this.loginId = loginId;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String email) {
		this.department = email;
	}
	
	
	
}
