package com.learning.backend.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Expense {

	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private Long categoryId;
	private LocalDate expDate;
	private Long amount;
	private String description;
	
	private String year;
	private String month;
	
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;
	
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Expense(Long id, Long userId, Long categoryId, LocalDate expDate, Long amount, String description,
			String createdBy, LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.expDate = expDate;
		this.amount = amount;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}	
	public LocalDate getExpDate() {
		return expDate;
	}
	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
}
