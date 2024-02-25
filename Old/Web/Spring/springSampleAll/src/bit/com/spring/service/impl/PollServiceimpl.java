package bit.com.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.PollDao;
import bit.com.spring.dto.PollBean;
import bit.com.spring.dto.PollDto;
import bit.com.spring.dto.PollSubDto;
import bit.com.spring.dto.Voter;
import bit.com.spring.service.PollService;

@Service
public class PollServiceimpl implements PollService {

	@Autowired
	PollDao dao;
	
	@Override
	public List<PollDto> getPollAllList(String id) {
		//모든 투표 목록을 가지고 온다.
		List<PollDto> list = dao.getPollAllList();
		
		//편집을 해서 보내주어야 한다.(투표가 가능한지 설정을 해주어야한다)
		List<PollDto> plist = new ArrayList<PollDto>();
		
		for (PollDto poll : list) {		// 다 가지고온 리스트에서 하나씩 꺼낸다
			int pollid = poll.getPollid();	//투표 번호를 꺼낸다
			
			//투표를 했으면 1, 하지 않았으면 0
			int count = dao.isVote(new Voter(pollid, -1, id)); // -1은 의미 없는 번호 (보기 번호는 필요ㅕ없음)
			if(count ==1) { //투표를 함
				poll.setVote(true);
			}
			else {			//투표 안함
				poll.setVote(false);
			}
			plist.add(poll);
		}
		return plist;		// 나중에 투표 여부를 통해 결과를 보여주고 안보여주고를 결정
	}

	@Override
	public void makePoll(PollBean pbean) {

		//투표 질문 추가
		PollDto poll = new PollDto(pbean.getId(), pbean.getQuestion(), pbean.getSdate(), pbean.getEdate(), pbean.getItemcount(), 0);
		
		dao.makePoll(poll);
		
		//투표 선택안들 (보기들)
		String answer[] = pbean.getPollnum();
		for (int i = 0; i < pbean.getItemcount(); i++) {	//length로 반복을 잡아주면안된다.
			PollSubDto pollsub = new PollSubDto();
			pollsub.setAnswer(answer[i]);	//넘어온 answer값들을 for을 돌리면서 하나씩 pollsub에 셋팅해 준다.
			
			dao.makePollSub(pollsub);
		}
	}

	@Override
	public PollDto getPoll(PollDto poll) {
		return dao.getPoll(poll);
	}

	@Override
	public List<PollSubDto> getPollSubList(PollDto poll) {
		return dao.getPollSubList(poll);
	}

	@Override
	public void polling(Voter voter) {
		dao.pollingVoter(voter);
		dao.pollingPoll(voter);
		dao.pollingSub(voter);
	}

	
}
