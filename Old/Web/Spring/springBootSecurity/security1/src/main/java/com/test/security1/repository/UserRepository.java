package com.test.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.security1.model.User;


//CRUD 함수를 JpaRepository가 들고 있음
// @Repository 라는 어노테이션이 없어도 Ioc가 된다. 이유는 JpaRepository를 상속을 받았기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{
	//findBy 규칙 => Username 문법
	// select * from user where username = ? 로 실행.
	public User findByUsername(String username); // Jpa Query methods
}
