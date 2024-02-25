package bit.com.spring.dao;

import bit.com.spring.dto.MemberDto;

public interface MemberDao {
	boolean addMember(MemberDto dto);
	int checkId(String id);
	MemberDto login(MemberDto dto);
}
