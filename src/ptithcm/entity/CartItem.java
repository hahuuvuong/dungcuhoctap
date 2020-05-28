package ptithcm.entity;

import javax.persistence.*;

public class CartItem {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	private Integer discount;
	private Integer cartItemId;
	
	private Integer quantity;
	
	private Float unitPrice;

	private int proid;

	private int cartid;

	
	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getProid() {
		return proid;
	}

	public void setProid(int proid) {
		this.proid = proid;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public CartItem() {
		super();
	}

	public CartItem(Integer cartItemId, Integer quantity, Float unitPrice, int proid, int cartid) {
		super();
		this.cartItemId = cartItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.proid = proid;
		this.cartid = cartid;
	}

	public CartItem(String name, Integer discount, Integer cartItemId, Integer quantity, Float unitPrice, int proid) {
		super();
		this.name = name;
		this.discount = discount;
		this.cartItemId = cartItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.proid = proid;
	}


}
