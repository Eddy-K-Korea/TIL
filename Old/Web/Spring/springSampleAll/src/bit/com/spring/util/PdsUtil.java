package bit.com.spring.util;

import java.util.Date;

//파일명 중복에 대한 방지를 위한 util
public class PdsUtil {

	//ex:  myfile.txt -> f.indexOf('.') -> 0부터 6까지 온다
	//파일명, 확장자명
	//f.substring(6) =>  .txt 를 가져온다
	//f.substring(0,6) => myfile 을 가져온다
	
	//myfile.txt -> 23423232323.txt 로 저장 할것이다(앞에 숫자는 시스템시간)(충돌이 나지 않는다)
	//oldfilename에는  myfile.txt 를 저장하고 filename 에는 23423232323.txt를 저장한다

	public static String getNewFileName(String f) {
		String filename = "";
		String fpost = ""; //파일 확장자명
		
		if(f.indexOf('.') >=0	) { //확장자명이 있음
			fpost = f.substring(f.indexOf('.'));	// fpost = .txt가 들어가있다
			filename = new Date().getTime() + fpost; // 32423423.txt로 들어가게 된다
		}
		else {
			filename = new Date().getTime() + ".back"; //인위적으로 붙여준다
		}
		return filename;
	}
} 
