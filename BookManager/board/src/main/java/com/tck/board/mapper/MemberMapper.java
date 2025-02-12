package com.tck.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tck.board.model.MemberDTO;

import interceptor.CustomUserDetails;



public interface MemberMapper {

	public List<MemberDTO> getMemberList();
	
	public void insertMember(MemberDTO memberDTO);
	
	public CustomUserDetails loginID(String id);
	
}
