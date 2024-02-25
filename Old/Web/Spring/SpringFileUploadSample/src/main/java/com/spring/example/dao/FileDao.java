package com.spring.example.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.example.dto.FileDto;

@Repository
public class FileDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public boolean fileinsert(FileDto fileDto) {
		System.out.println("fileinsert");
		int result = sqlSession.insert("fileinsert", fileDto);
		return result>0?true:false;
	}
	
	public FileDto callfile(int seq) {
		System.out.println("callfile");
		return sqlSession.selectOne("callfile", seq);
	}
}
