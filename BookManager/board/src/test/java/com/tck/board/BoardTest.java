package com.tck.board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tck.board.model.BoardDTO;
import com.tck.board.model.MemberDTO;
import com.tck.board.service.MemberService;
import com.tck.board.mapper.BoardMapper;
import com.tck.board.mapper.MemberMapper;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardTest {

	
	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MemberService memberService;
	
	@Test
	public void test()
	{
//		List<BoardDTO> boardDTO = new ArrayList<BoardDTO>();
//		
//		boardDTO = boardMapper.getBoardList();
//		
//		System.out.println("게시판 리스트: "+boardDTO);
		
		
//		List<MemberDTO> memberDTO = new ArrayList<MemberDTO>();
//		
//		memberDTO = memberMapper.getMemberList();
//		
//		System.out.println("멤버 리스트 : "+memberDTO);
		
//		System.out.println("멤버 리스트 : "+memberService.getMemberList());
		
		
//		BoardDTO boardDTO = new BoardDTO();
//		
//		boardDTO.setContent("하이");
//		boardDTO.setTitle("제목");
//		boardDTO.setWriteUser("aa");
//		
//		boardMapper.writeBoard(boardDTO);
		
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO = boardMapper.getBoardDetail(1);
		
		System.out.println(boardDTO);
		
	}
}
