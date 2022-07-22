package model;

public class OrderDetail {
	private int id;
	private String orders_code;
	private int products_id;
	private int quantity;
	public OrderDetail(int id, String orders_code, int products_id, int quantity) {
		super();
		this.id = id;
		this.orders_code = orders_code;
		this.products_id = products_id;
		this.quantity = quantity;
	}
	public OrderDetail() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrders_code() {
		return orders_code;
	}
	public void setOrders_code(String orders_code) {
		this.orders_code = orders_code;
	}
	public int getProducts_id() {
		return products_id;
	}
	public void setProducts_id(int products_id) {
		this.products_id = products_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
