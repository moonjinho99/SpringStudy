package com.tck.board.mapper;

import java.util.List;

import com.tck.board.model.BoardDTO;

public interface BoardMapper {
	
	public List<BoardDTO> getBoardList();
	
	public void writeBoard(BoardDTO boardDTO);
	
	public BoardDTO getBoardDetail(int seq_num);
	
	public void updateBoard(BoardDTO boardDTO);
	
	public void deleteBoard(int seq_num);
}
