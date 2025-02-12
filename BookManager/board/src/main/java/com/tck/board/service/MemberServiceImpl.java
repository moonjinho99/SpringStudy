package com.tck.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tck.board.mapper.MemberMapper;
import com.tck.board.model.MemberDTO;

import util.LoginUtil;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	//전체 멤버리스트 가져오기
	@Override
	public List<MemberDTO> getMemberList() {
		return memberMapper.getMemberList();
	}

	@Override
	public void insertMember(MemberDTO memberDTO) {
		memberMapper.insertMember(memberDTO);
	}
	

}
