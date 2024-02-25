package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.BbsDao;
import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.BbsParam;
import bit.com.spring.service.BbsService;

@Service
public class BbsServiceimpl implements BbsService {
	
	@Autowired
	BbsDao bbsDao;
	@Override
	public List<BbsDto> allBbsList(BbsParam bbs) {
		List<BbsDto> list = bbsDao.allBbsList(bbs);
		return list;
	}
	@Override
	public boolean addbbs(BbsDto bbsdto) {
		
		boolean isS = bbsDao.addbbs(bbsdto);
		return isS;
	}
	@Override
	public BbsDto getbbs(String seq) {
		BbsDto dto = bbsDao.getbbs(seq);
		return dto;
	}
	@Override
	public boolean addreply(BbsDto bbsdto) {
		boolean isS = bbsDao.addreply(bbsdto);
		return isS;
	}
	@Override
	public boolean updatestep(BbsDto bbsdto) {
		boolean isS = bbsDao.updatestep(bbsdto);
		return isS;
	}
	/*@Override
	public List<BbsDto> getsearchlist(BbsParam bbs) {
		List<BbsDto> list = bbsDao.getsearchlist(bbs);
		return list;
	}*/
	@Override
	public boolean removebbs(String seq) {
		boolean isS = bbsDao.removebbs(seq);
		return isS;
	}
	@Override
	public boolean updatebbs(BbsDto bbs) {
		boolean isS = bbsDao.updatebbs(bbs);
		return isS;
	}
	@Override
	public int getBbsCount(BbsParam bbs) {
		return bbsDao.getBbsCount(bbs);
	}

	
}
