package com.tck.board.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tck.board.book.mapper.BookMapper;
import com.tck.board.book.model.BookDTO;
import com.tck.board.img.model.ImgDTO;

@Service
public class BookService {

	@Autowired
	BookMapper bookMapper;
	
	public List<BookDTO> getBookList()
	{
		return bookMapper.getBookList();
	}
	
	public void insertBook(BookDTO bookDTO)
	{
		bookMapper.insertBook(bookDTO);
	}
	
	public BookDTO getBookDetail(int book_code)
	{
		return bookMapper.getBookDetail(book_code);
	}
	
	public int getMaxBookCode()
	{
		return bookMapper.maxBookCode();
	}
	
	public void insertImg(ImgDTO imgDTO)
	{
		bookMapper.insertImg(imgDTO);
	}
	
	public ImgDTO getImgList(int book_code)
	{
		return bookMapper.getImgList(book_code);
	}
	
	public void updateBook(BookDTO bookDTO)
	{
		bookMapper.updateBookList(bookDTO);
	}
	
	public void updateImg(ImgDTO imgDTO)
	{
		bookMapper.updateImg(imgDTO);
	}
	
	
	public void deleteBoook(int book_code)
	{
		bookMapper.deleteBookImg(book_code);
		bookMapper.deleteBookList(book_code);
	}
	
}
