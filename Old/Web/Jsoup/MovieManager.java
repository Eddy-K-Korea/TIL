package movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dto.MovieVo;

public class MovieManager {

	public static List<MovieVo> getCGVdata() {
		Document doc;
		List<MovieVo> list = new ArrayList<MovieVo>();
		try {
			doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			
			// 영화 제목					클래스라서 . id 이면 #
			Elements titles = doc.select("div.box-contents strong.title");
					
			/*   아래 부분이 영화 제목 끌어올 부분
       <div class="box-contents">
         <a href="/movies/detail-view/?midx=83160">
           <strong class="title">반도</strong>
         </a>
			*/
			// 좋아요 %
			Elements likes = doc.select("div.box-contents span.percent");
			
			/* 좋아요를 끌어올 부분
			 <div class="score">
         <strong class="percent">예매율<span>1.6%</span></strong>
           <div class='egg-gage small'>
             <span class='egg great'></span>
             <span class='percent'>94%</span>
           </div>
       </div> 
			  
			 */
			
			for (int i = 0; i < 7; i++) {
				Element eTitle = titles.get(i);
				String title = eTitle.text();
				System.out.print(title + " ");
				
				Element elike = likes.get(i);
				String like = elike.text().replace("%", "");
				System.out.println(like);
				
				MovieVo vo = new MovieVo(title, Integer.parseInt(like));
				
				list.add(vo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
