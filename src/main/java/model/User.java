package model;

public class User {

	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_USER = "user";

	public int id;
	public String phone;
	public String name;
	public String password;

	public String address;
	public String role;

	public User(int id, String phone, String password, String role) {
		super();
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public User(String phone, String password, String role) {
		super();
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public User() {
		super();
	}

	public User(int id, String phone, String name, String password, String address, String role) {
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public User(String phone, String name, String password, String address, String role) {
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String username) {
		this.phone = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
