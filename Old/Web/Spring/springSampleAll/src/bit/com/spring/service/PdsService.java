package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.PdsDto;
import bit.com.spring.dto.PdsParam;

public interface PdsService {

	List<PdsDto> getPdsList(PdsParam param);
	boolean uploadPds(PdsDto dto);
	PdsDto getPdsdetail(int seq);
	
	boolean updatePds(PdsDto dto);
}
