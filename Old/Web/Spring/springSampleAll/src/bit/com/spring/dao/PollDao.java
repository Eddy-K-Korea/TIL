package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.PollDto;
import bit.com.spring.dto.PollSubDto;
import bit.com.spring.dto.Voter;

public interface PollDao {

	List<PollDto> getPollAllList();
	
	int isVote(Voter voter);
	
	void makePoll(PollDto poll);
	void makePollSub(PollSubDto pollsub);
	
	PollDto getPoll(PollDto poll);
	List<PollSubDto> getPollSubList(PollDto poll);
	
	void pollingVoter(Voter voter);
	void pollingPoll(Voter voter);
	void pollingSub(Voter voter);
}
