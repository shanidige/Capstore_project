package com.capgemini.capstore.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="product")
public class ProductBean {

	@Id
	@Column
	private int productId;
	@Column
	private String productName;
	@Column
	private double productPrice;
	@Column
	private String ProductQuantity;
	@Column
	private String productCategory;
	@Column
	private String productImage;
	@Column
	private double productDiscount;
	@Column
	private Date productDiscountExpiryDate;
	@Column
	private String productBrandName;
	@Column
	private String email;
	
	
	// Getters and Setters
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductQuantity() {
		return ProductQuantity;
	}
	public void setProductQuantity(String productQuantity) {
		ProductQuantity = productQuantity;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public double getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}
	public Date getProductDiscountExpiryDate() {
		return productDiscountExpiryDate;
	}
	public void setProductDiscountExpiryDate(Date productDiscountExpiryDate) {
		this.productDiscountExpiryDate = productDiscountExpiryDate;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
