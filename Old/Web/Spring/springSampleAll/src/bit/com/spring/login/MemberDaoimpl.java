package bit.com.spring.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dto.MemberDto;

@Repository
public class MemberDaoimpl implements MemberDao{

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "Member.";
	
	@Override
	public int checkId(String id) {
		
		int result = sqlSession.selectOne(namespace+"checkId", id);
		return result;
	}

	@Override
	public boolean addMember(MemberDto dto) {

		int result = sqlSession.insert(namespace+"addMem", dto);
		return result>0?true:false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		
		MemberDto member = sqlSession.selectOne(namespace+"login", dto);
		return member;
	}
	
	
}
