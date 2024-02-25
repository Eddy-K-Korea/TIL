package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.PollBean;
import bit.com.spring.dto.PollDto;
import bit.com.spring.dto.PollSubDto;
import bit.com.spring.dto.Voter;

public interface PollService {
	
	List<PollDto> getPollAllList(String id);
	
	void makePoll(PollBean pbean); //Pollbean은 polldto 와 pollsub를 합친것이다.
	
	PollDto getPoll(PollDto poll);
	List<PollSubDto> getPollSubList(PollDto poll);
	
	void polling(Voter voter);
}
