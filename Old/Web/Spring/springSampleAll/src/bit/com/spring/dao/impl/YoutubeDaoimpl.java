package bit.com.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.YoutubeDao;
import bit.com.spring.dto.YoutubeSave;

@Repository
public class YoutubeDaoimpl implements YoutubeDao {

	@Autowired
	SqlSession sqlSession;

	String ns = "Youtube.";
	@Override
	public boolean saveVideo(YoutubeSave ys) {
		int result = sqlSession.insert(ns+"saveVideo", ys);
		return result>0?true:false;
	}
	@Override
	public List<YoutubeSave> getMyVideos(String id) {
		return sqlSession.selectList(ns+"getMyVideos", id);
	}
	@Override
	public boolean removeVideo(int seq) {
		int result = sqlSession.delete(ns+"removeVideo", seq);
		return result>0?true:false;
	}
	
	
}
