package ptithcm.entity;
import java.util.Collection;

import javax.persistence.*;

public class Category {

	private Integer cateId;
	
	private String cateName;
	
	private String cateImage;
	
	

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCateImage() {
		return cateImage;
	}

	public void setCateImage(String cateImage) {
		this.cateImage = cateImage;
	}

	public Category(Integer cateId, String cateName, String cateImage) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
		this.cateImage = cateImage;
	}

	public Category() {
		super();
	}
	
	
}
