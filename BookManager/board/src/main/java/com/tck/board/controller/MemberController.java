package com.tck.board.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tck.board.model.MemberDTO;
import com.tck.board.service.MemberService;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	HttpSession session;
	
	
	//로그인 페이지 진입
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage()
	{
		return "member/login";
	}
	
	//로그인 기능 
	@PreAuthorize("ROLE_MEMBER")
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginDo(@RequestParam(value="userId")String id, @RequestParam(value="userPassword")String pw,HttpServletRequest request,Principal principal)
	{
		String returnUrl = "";
		
		int cnt=0;
		
		session = request.getSession();
		
		List<MemberDTO> memberList = memberService.getMemberList();
		
		if(session.getAttribute("member") != null)
		{
			session.removeAttribute("member");
		}
		
		for(int i=0; i<memberList.size(); i++)
		{
			
			if(memberList.get(i).getId().equals(id) &&  pwdEncoder.matches(pw, memberList.get(i).getPw()))
			{
				System.out.println("로그인 성공");
				session.setAttribute("member", memberList.get(i));
				
				returnUrl = "redirect:/board/list";
			
				cnt++;
			}
		}
		
		if(cnt == 0)
		{
			returnUrl = "member/loginfail";
		}
		
		return returnUrl;
	}
	
	
	//회원가입 페이지 진입
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinPage()
	{
	
		return "member/join";
	}

	//회원가입 기능
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String joinDo(@RequestParam(value="joinId")String id, @RequestParam(value="joinPassword")String pw,@RequestParam(value="joinName")String name)
	{
		System.out.println("회원가입");
		
		MemberDTO memberDTO = new MemberDTO();
		
		String hashedPassword = pwdEncoder.encode(pw);
		
		memberDTO.setId(id);
		memberDTO.setPw(hashedPassword);
		memberDTO.setName(name);
		
		memberService.insertMember(memberDTO);
		
		System.out.println("회원가입 완료 : "+memberDTO);
		
		return "redirect:/member/login";
	}
	
	
	//로그아웃
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logoutDo(HttpServletRequest request)
	{
		session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/member/login";
	}
}
