package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.PdsDto;
import bit.com.spring.dto.PdsParam;

public interface PdsDao {

	List<PdsDto> getPdsList(PdsParam param);
	boolean uploadPds(PdsDto dto);
	PdsDto getPdsdetail(int seq);
	
	boolean updatePds(PdsDto dto);
}
