package model;

public class Order {
	private int id;
	private String code;
	private String description;
	private String status;
	private int user_id;
	
	public Order() {
		super();
	}
	public Order(int id, String code, String description, String status, int user_id) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.status = status;
		this.user_id = user_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
