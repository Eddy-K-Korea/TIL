package bit.com.spring.service;

import bit.com.spring.dto.MemberDto;

public interface MemberService {

	String addMember(MemberDto dto);
	int checkid(String id);
	MemberDto login(MemberDto dto);
}
