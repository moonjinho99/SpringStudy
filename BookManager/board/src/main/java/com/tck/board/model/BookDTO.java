package com.tck.board.model;

import java.sql.Date;

public class BookDTO {
	
	private int book_code;
	private String book_title;
	private String category;
	private String book_author;
	private Date book_reg;
	private String book_content;
	private int book_price;
	
	
	public int getBook_code() {
		return book_code;
	}
	public void setBook_code(int book_code) {
		this.book_code = book_code;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public Date getBook_reg() {
		return book_reg;
	}
	public void setBook_reg(Date book_reg) {
		this.book_reg = book_reg;
	}
	public String getBook_content() {
		return book_content;
	}
	public void setBook_content(String book_content) {
		this.book_content = book_content;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
	@Override
	public String toString() {
		return "BookDTO [book_code=" + book_code + ", book_title=" + book_title + ", category=" + category
				+ ", book_author=" + book_author + ", book_reg=" + book_reg + ", book_content=" + book_content
				+ ", book_price=" + book_price + "]";
	}
	
	

}
