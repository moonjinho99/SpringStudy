package com.tck.board.img.model;

public class ImgDTO {
	
	private int img_code;
	private int book_code;
	private String img_name;
	private String img_uuid;
	private String img_path;
	
	public int getImg_code() {
		return img_code;
	}
	public void setImg_code(int img_code) {
		this.img_code = img_code;
	}
	public int getBook_code() {
		return book_code;
	}
	public void setBook_code(int book_code) {
		this.book_code = book_code;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getImg_uuid() {
		return img_uuid;
	}
	public void setImg_uuid(String img_uuid) {
		this.img_uuid = img_uuid;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	@Override
	public String toString() {
		return "ImgDTO [img_code=" + img_code + ", book_code=" + book_code + ", img_name=" + img_name + ", img_uuid="
				+ img_uuid + ", img_path=" + img_path + "]";
	}
	
}
