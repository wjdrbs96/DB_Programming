package DAO;

public class AddressBook {
	private int id;
	private String name;
	private String tel;
	private String email;
	private String address;
	
	
	public AddressBook(String name, String tel, String email, String address) {
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.address = address;
	}
	
	public AddressBook(int id, String name, String tel, String email, String address) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
