package bit.com.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.MemberDao;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.MemberService;

@Service
public class MemberServiceimpl implements MemberService{

	
	@Autowired
	MemberDao memberDao;
	@Override
	public String addMember(MemberDto dto) {
		String msg="";
		boolean isS = memberDao.addMember(dto);
		if(isS) {
			msg="회원가입 성공";
		}else {
			msg="회원가입 실패";
		}
		
		return msg;
	}
	@Override
	public int checkid(String id) {
		int result = memberDao.checkId(id);
		return result;
	}
	@Override
	public MemberDto login(MemberDto dto) {
		MemberDto member = memberDao.login(dto);
		return member;
	}
	
	

}
