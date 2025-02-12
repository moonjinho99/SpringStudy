package com.tck.board.book.model;

import java.sql.Date;
import java.util.List;

import com.tck.board.img.model.ImgDTO;

public class BookDTO {
	
	private int book_code;
	private String book_title;
	private String book_category;
	private String book_author;
	private Date book_reg;
	private String book_content;
	private int book_quantity;
	private int book_price;
	
	private String img;
	private ImgDTO book_img;
	private String rowNum;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
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
	public String getBook_category() {
		return book_category;
	}
	public void setBook_category(String book_category) {
		this.book_category = book_category;
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
	public int getBook_quantity() {
		return book_quantity;
	}
	public void setBook_quantity(int book_quantity) {
		this.book_quantity = book_quantity;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
		
	public ImgDTO getBook_img() {
		return book_img;
	}
	public void setBook_img(ImgDTO book_img) {
		this.book_img = book_img;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	@Override
	public String toString() {
		return "BookDTO [book_code=" + book_code + ", book_title=" + book_title + ", book_category=" + book_category
				+ ", book_author=" + book_author + ", book_reg=" + book_reg + ", book_content=" + book_content
				+ ", book_quantity=" + book_quantity + ", book_price=" + book_price + ", book_img=" + book_img
				+ ", rowNum=" + rowNum + "]";
	}
}
