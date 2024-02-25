package bit.com.spring.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.BbsDao;
import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.BbsParam;

@Repository
public class BbsDaoimpl implements BbsDao {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "bbs.";
	
	@Override
	public List<BbsDto> allBbsList(BbsParam bbs) {
		List<BbsDto>list = sqlSession.selectList(namespace+"allbbsList",bbs);
		
		return list;
	}

	@Override
	public boolean addbbs(BbsDto bbsdto) {
		
		int result = sqlSession.insert(namespace+"addbbs", bbsdto);	
		return result>0?true:false;
	}

	@Override
	public BbsDto getbbs(String seq) {
		int seqnum = Integer.parseInt(seq);
		BbsDto dto = sqlSession.selectOne(namespace+"getbbs", seqnum);
		return dto;
	}

	@Override
	public boolean addreply(BbsDto bbsdto) {
		
		int result = sqlSession.insert(namespace+"addreply", bbsdto);
		return result>0?true:false;
	}

	@Override
	public boolean updatestep(BbsDto bbsdto) {
		int result = sqlSession.update(namespace+"updatestep", bbsdto);
		return result>0?true:false;
	}

/*	@Override
	public List<BbsDto> getsearchlist(BbsParam bbs) {
		
		List<BbsDto>list = sqlSession.selectList(namespace+"getsearchlist", bbs);
		
		return list;
	}
*/
	@Override
	public boolean removebbs(String seq) {
		int result = sqlSession.update(namespace+"removebbs", seq);
		return  result>0?true:false;
	}

	@Override
	public boolean updatebbs(BbsDto bbs) {
		int result = sqlSession.update(namespace+"updatebbs", bbs);
		return result>0?true:false;
	}

	@Override
	public int getBbsCount(BbsParam bbs) {
		
		return sqlSession.selectOne(namespace+"getBbsCount", bbs);
	}
	
}
