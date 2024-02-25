package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.YoutubeSave;

public interface YoutubeDao {
	boolean saveVideo(YoutubeSave ys);
	List<YoutubeSave> getMyVideos(String id);
	boolean removeVideo(int seq);
}
