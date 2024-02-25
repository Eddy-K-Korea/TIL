package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.YoutubeSave;

	public interface YoutubeService {
		boolean saveVideo(YoutubeSave ys);
		List<YoutubeSave> getMyVideos(String id);
		boolean removeVideo(int seq);
}
