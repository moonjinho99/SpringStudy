package com.tck.board.service;

import java.util.List;

import com.tck.board.model.MemberDTO;

public interface MemberService {
	
	public List<MemberDTO> getMemberList();
	
	public void insertMember(MemberDTO memberDTO);

}
