package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.YoutubeDao;
import bit.com.spring.dto.YoutubeSave;
import bit.com.spring.service.YoutubeService;

@Service
public class YoutubeServiceimpl implements YoutubeService {

	@Autowired
	YoutubeDao dao;

	@Override
	public boolean saveVideo(YoutubeSave ys) {
		return dao.saveVideo(ys);
	}

	@Override
	public List<YoutubeSave> getMyVideos(String id) {
		return dao.getMyVideos(id);
	}

	@Override
	public boolean removeVideo(int seq) {
		return dao.removeVideo(seq);
	}
	
	
}
