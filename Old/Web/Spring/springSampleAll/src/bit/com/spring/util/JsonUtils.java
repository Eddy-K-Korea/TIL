package bit.com.spring.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;	//처음 jsonObject simple이 아닌 org.json으로 해야한다
import org.json.JSONObject;



// https://www.it-swarm.dev/ko/java/json-%EB%AC%B8%EC%9E%90%EC%97%B4%EC%9D%84-hashmap%EC%9C%BC%EB%A1%9C-%EB%B3%80%ED%99%98/1045437367/

//JSONObject를 Map과 List로 저장하는  class

// http://json.parser.online.fr/ 	json 정렬해주는 사이트
public class JsonUtils {
	
	//원하는 값을 취득하기 위한 list를 준비
	public static List<String> list = new ArrayList<String>();
	public static List<String> titleList = new ArrayList<String>();
		
	

	public static Map<String, Object> jsonToMap(JSONObject json) {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != null) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
            
            //데이터를 취득 할 수 있는 부분 , key
            
           // System.out.println("key:"+key);
            //videoId
            if(key.equals("videoId")) {
            	Iterator<String> it = list.iterator();
            	while(it.hasNext()) {
            		String k = it.next();
            		if(k.equals(value)) {
            			it.remove();
            		}
            	}
            	list.add((String)value);
            }
            
            
            //key 값이 title 인것만 출력
            if(key.equals("title")) {
            	//System.out.println("key:"+key + "value:"+value);
            	
            	if(value.toString().contains("accessibility")) {
            	//	System.out.println("key:"+key + " value:"+value);
            		int start = value.toString().indexOf("text=");
            		int end = value.toString().indexOf("}]");
            		
            		String title = value.toString().substring(start+5, end);
            	//	System.out.println(title);
            		
            		// 중복된 제목을 추가 하지 않도록
            		Iterator<String> it = titleList.iterator();
            		while(it.hasNext()) {
            			String k = it.next();
            			if(k.equals(title)) {
            				it.remove();
            			}
            		}
            		
            		titleList.add(title);
            	}
            }
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
