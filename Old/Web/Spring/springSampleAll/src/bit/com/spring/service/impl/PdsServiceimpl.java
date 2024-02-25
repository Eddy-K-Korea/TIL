package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.PdsDao;
import bit.com.spring.dto.PdsDto;
import bit.com.spring.dto.PdsParam;
import bit.com.spring.service.PdsService;

@Service
public class PdsServiceimpl implements PdsService {

	@Autowired
	PdsDao dao;
	
	@Override
	public List<PdsDto> getPdsList(PdsParam param) {
		return dao.getPdsList(param);
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		return dao.uploadPds(dto);
	}

	@Override
	public PdsDto getPdsdetail(int seq) {
		return dao.getPdsdetail(seq);
	}

	@Override
	public boolean updatePds(PdsDto dto) {
		return dao.updatePds(dto);
	}

	
}
