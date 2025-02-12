package com.tck.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tck.board.model.BoardDTO;
import com.tck.board.model.MemberDTO;
import com.tck.board.model.WrapperVO;
import com.tck.board.service.BoardService;
import com.tck.board.service.MemberService;


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	HttpSession session;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model,HttpServletRequest request)
	{	
		String url = "";
		
		model.addAttribute("boardList",boardService.getBoardList());
		System.out.println("게시판 리스트:"+boardService.getBoardList());
		url = "/board/boardList";
						
		return url;
	}
	
	
	@RequestMapping(value="/getBoardList.do",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getBoardList(@ModelAttribute BoardDTO boardDTO) throws Exception
	{
		List<BoardDTO> board = boardService.getBoardList();
		Gson gson = new Gson();
		WrapperVO rntVO = new WrapperVO();
		rntVO.setAaData(board);
		String jsonString = gson.toJson(rntVO);
		
		return jsonString;
	}
	
	@RequestMapping(value="boardWrite", method = RequestMethod.GET)
	public String writePage()
	{
		return "/board/boardWrite";
	}
	
	@RequestMapping(value="write.do", method=RequestMethod.POST)
	public String writeDo(@RequestParam(value="title")String title, @RequestParam(value="content")String content, @RequestParam(value="writeUser")String writeUser)
	{
		System.out.println("�꽆�뼱�삩 媛� : "+title+","+content+","+writeUser);
		
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setContent(content);
		boardDTO.setTitle(title);
		boardDTO.setWriteUser(writeUser);
		
		
		boardService.writeBoard(boardDTO);
		System.out.println("湲� �벑濡앹셿猷�");
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="boardDetail", method=RequestMethod.GET)
	public String boardDetail(HttpServletRequest request,Model model)
	{
		int seq_num =Integer.parseInt(request.getParameter("seq_num"));
		
		
		BoardDTO boardDTO = boardService.getBoardDetail(seq_num);
		
		model.addAttribute("boardDetail",boardDTO);
		
		return "/board/boardDetail";
	}
	
	
	@RequestMapping(value="boardUpdate",method=RequestMethod.GET)
	public String boardUpdate(@RequestParam(value="seq_num")int seq_num,Model model)
	{
		model.addAttribute("updateBoard",boardService.getBoardDetail(seq_num));
		
		return "/board/boardUpdate";
	}
	
	@RequestMapping(value="boardDelete",method=RequestMethod.GET)
	public String boardDelete(@RequestParam(value="seq_num")int seq_num)
	{
		
		boardService.deleteBoard(seq_num);
		System.out.println("寃뚯떆湲� �궘�젣 �셿猷�");
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="update.do",method=RequestMethod.POST)
	public String boardUpdateDo(HttpServletRequest request)
	{
	
		int seq_num = Integer.parseInt(request.getParameter("seq_num"));
		String writeUser = request.getParameter("writeUser");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setContent(content);
		boardDTO.setSeq_num(seq_num);
		boardDTO.setTitle(title);
		boardDTO.setWriteUser(writeUser);
		
		System.out.println("�닔�젙媛� : "+boardDTO);
		
		boardService.updateBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
}
