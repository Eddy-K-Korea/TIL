package bit.com.spring.util;

import java.util.Calendar;
import java.util.Date;

public class PollUtil {
	
	
	//달력에 날짜를 표시를  20200915 형식으로 만드는 메소드
	public static String StringCal(Calendar dd) {
		String s = "";
		int year = dd.get(Calendar.YEAR);
		int month = dd.get(Calendar.MONTH) +1;
		int day = dd.get(Calendar.DATE);
		s = year + ""+CalendarUtil.two(month+"")+CalendarUtil.two(day+"");
		
		/*
		 CalendarUtil
		 
		 public static String two(String msg) {
			return msg.trim().length()<2?"0"+msg.trim():msg.trim();
		 }
		 */
		return s;
	}
	
	//연월일만으로 비교해줄 메소드	(오늘 날짜 > 종료 일  = 투표 마감
	public static boolean isEnd(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		Calendar now = Calendar.getInstance();
		
		//오늘 날짜가 마감일 보다 큰지 비교
		
		boolean b = Integer.parseInt(StringCal(now)) > Integer.parseInt(StringCal(c));
		return b;
	}
	
	
	
	//투표의 종료 판별하는 메소드
	public static String pollState(Date d) {
		String s1 = "<div style='color:RED'>[종료]</div>";
		String s2 = "<div style='color:BLUE'>[진행중]</div>";
		
		return isEnd(d)?s1:s2;	//isEnd가 true가 나온건 종료, false는 진행중
	}
	
	
	
	
	
	
}
