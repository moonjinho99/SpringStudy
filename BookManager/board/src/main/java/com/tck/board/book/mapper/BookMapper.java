package com.tck.board.book.mapper;

import java.util.List;

import com.tck.board.book.model.BookDTO;
import com.tck.board.img.model.ImgDTO;

public interface BookMapper {

	public List<BookDTO> getBookList();
	
	public ImgDTO getImgList(int book_code);
	
	public void insertBook(BookDTO bookDTO);
	
	public BookDTO getBookDetail(int book_code);
	
	public int maxBookCode();
	
	public void insertImg(ImgDTO imgDTO);
	
	public void updateBookList(BookDTO bookDTO);
	
	public void updateImg(ImgDTO imgDTO);
	
	public void deleteBookList(int book_code);
	
	public void deleteBookImg(int book_code);
}
