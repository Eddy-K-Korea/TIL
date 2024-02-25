package bit.com.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.PdsDao;
import bit.com.spring.dto.PdsDto;
import bit.com.spring.dto.PdsParam;

@Repository
public class PdsDaoimpl implements PdsDao {

	@Autowired
	SqlSession sqlSession;
	
	String ns ="Pds.";

	@Override
	public List<PdsDto> getPdsList(PdsParam param) {
		return sqlSession.selectList(ns+"getPdsList",param);
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		int result = sqlSession.insert(ns+"uploadPds", dto);
		return result >0?true:false;
	}

	@Override
	public PdsDto getPdsdetail(int seq) {
		return sqlSession.selectOne(ns+"getPdsdetail", seq);
	}

	@Override
	public boolean updatePds(PdsDto dto) {
		int result = sqlSession.update(ns+"updatepds", dto);
		return false;
	}
	
	
}
