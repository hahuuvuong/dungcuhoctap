package ptithcm.entity;

import java.util.Collection;

import javax.persistence.*;


public class Product {
	
	
	private Integer proId;
	
	private String name;
	
	private Float price;
	
	private Integer discount;
	
	
	private Integer category;

	private String description;
	
	private String information;
	
	private String image;

	
	public Product(Integer proId, String name, Float price, Integer discount, Integer category, String description,
			String information, String image) {
		super();
		this.proId = proId;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.category = category;
		this.description = description;
		this.information = information;
		this.image = image;
	}

	
	public Product() {
		super();
	}


	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}



	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}
