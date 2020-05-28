package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;


public class Cart {
	
	
	private Integer cartId;
	
	
	public Integer userid;
	
	private Date buyDate;
	
	public Integer isPaid;
	
	

	public Cart() {
		super();
	}

	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	

	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public Cart(Integer cartId, Integer userid, Date buyDate, Integer isPaid) {
		super();
		this.cartId = cartId;
		this.userid = userid;
		this.buyDate = buyDate;
		this.isPaid = isPaid;
	}


	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}


}
