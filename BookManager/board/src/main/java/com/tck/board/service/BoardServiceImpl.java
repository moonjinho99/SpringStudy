package com.tck.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tck.board.mapper.BoardMapper;
import com.tck.board.model.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardDTO> getBoardList() {
		return boardMapper.getBoardList();
	}

	@Override
	public void writeBoard(BoardDTO boardDTO) {
		boardMapper.writeBoard(boardDTO);
	}

	@Override
	public BoardDTO getBoardDetail(int seq_num) {
		return boardMapper.getBoardDetail(seq_num);
	}

	@Override
	public void deleteBoard(int seq_num) {
		boardMapper.deleteBoard(seq_num);
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardMapper.updateBoard(boardDTO);
	}
	
}
