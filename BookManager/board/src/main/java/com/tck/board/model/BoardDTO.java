package com.tck.board.model;


public class BoardDTO {
	
	private int seq_num;
	private String title;
	private String content;
	private String writeUser;
	
	public int getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteUser() {
		return writeUser;
	}
	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}
	@Override
	public String toString() {
		return "BoardDTO [seq_num=" + seq_num + ", title=" + title + ", content=" + content + ", writeUser=" + writeUser
				+ "]";
	}
	
	

}
