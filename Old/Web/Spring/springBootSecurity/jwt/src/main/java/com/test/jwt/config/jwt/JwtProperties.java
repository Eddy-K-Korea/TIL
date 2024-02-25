package com.test.jwt.config.jwt;

public interface JwtProperties {

	String SECRET = "cos";
	int EXPIRATION_TIME = 60000*10;
	String TOKEN_PRDFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
