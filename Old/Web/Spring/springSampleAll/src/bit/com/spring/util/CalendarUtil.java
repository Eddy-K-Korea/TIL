package bit.com.spring.util;

import java.sql.Date;
import java.util.List;

import bit.com.spring.dto.CalendarDto;

public class CalendarUtil {

	public static String two(String msg) {
		return msg.trim().length()<2?"0"+msg.trim():msg.trim();
	}
	// 1 -> 01
	public static String two(int tt) {
		return(tt+"").length()>1?(tt+""):"0"+tt;
	}
	
	//2020 3 -> 202003
	
	public static String yyyymm(int  year, int month) {
		return ""+year + two(month);
	}
	
	public static String yyyymmdd(int year, int month, int day) {
		return ""+year + two(month)+ two(day);
	}
	
	// 03(String) -> 3(int)
	public static int toOne(String tt) {
		if(tt.charAt(0)=='0') {
			return Integer.parseInt(""+tt.charAt(1));
		}else {
			return Integer.parseInt(tt);
		}
	}
	
	// 그날의 일정을 모두 출력
	public static String callist(int year, int month, int day) {
		String str="";
		str+= String.format("<a href='%s?year=%d&month=%d&day=%d' title='"+ day+"일별일정'>", "callist.do",year,month,day);
		
		str += String.format("%2d", day);
		str += "</a>";
		
		return str;
	}
	public static String showPen(int year, int month, int day) {
		String str = "";
		
		String image = "<img src='./image/pen.png' width='18px' height='18px'>";
		str = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", 
							"./calwrite.do", year, month, day, image);
		
		/*
		  <a href='%s?year=%d&month=%d&day=%d'>
		  	<img src='../image/pen2.png' width='18px' height='18px'>
		  </a>		    
		 */
		return str;		
	}
	
	public static String makeTable(int year, int month, int day, List<CalendarDto> list) {
		String str = "";
		
		// 2020 7 31 -> 20200731
		String dates = (year + "") + two(month + "") + two(day + "");
		
		str += "<table>";
		str += "<col width='100'>";
		
		for (CalendarDto dto : list) {
			if(dto.getRdate().substring(0, 8).equals(dates)) {
				str += "<tr>";
				str += "<td style='line-height: 10px; overflow: hidden; border-collapse:collapse;border:1px blue solid'>";
				str += "<a href='./caldetail.do?seq=" + dto.getSeq() + "'>";
				str += "<font style='font-size:8px; color:blue'>";
				str += dot3(dto.getTitle());
				str += "</font>";
				str += "</a>";
				str += "</td>";
				str += "</tr>";
			}			
		}
		str += "</table>";
		return str;
	}	
	
	// 일정 제목이 길 때 ...으로 처리하는 함수
	public static String dot3(String msg) {
		String str = "";
		if(msg.length() >= 10) {
			str = msg.substring(0, 10);	// 0 ~ 5
			str += "...";
		}
		else {
			str = msg.trim();			
		}
		return str;
	}
	
	//2020-09-10 -> java.lang.Date로 변경
	public static Date toDate(int year, int month, int day) {
		String s = year + "-" + two(month + "") + "-" + two(day + "");
		Date d = Date.valueOf(s);
		return d;
	}
	
	public static String yyyymmddhhmm(int year, int month, int day, int hour, int min) {
		return yyyymmdd(year, month, day) + two(hour) + two(min);
	}

	
}























