package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dto.Batter;
import dto.Human;
import dto.Pitcher;

public class FileProc {
	
	private File file;		//노출 시킬 필요가 없다.

	public FileProc(String fileName) {
		file = new File("d:\\tmp\\"+fileName+".txt");
	}
	
	public void createFile() {
		try {
			if(file.createNewFile()) {
				System.out.println("파일 생성 성공");
			}else {
				System.out.println("파일 생성 실패");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Human> loadData() {	//외부로 값을 내보내야함
		ArrayList<Human> list = new ArrayList<Human>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str;
			while((str= br.readLine())!=null) {
				String data[] = str.split("-");
				int num = Integer.parseInt(data[0]);
				
				if(num < 2000) {
					
					Pitcher p = new Pitcher(Integer.parseInt(data[0]), 
													data[1], 
													Integer.parseInt(data[2]), 
													Double.parseDouble(data[3]), 
													Integer.parseInt(data[4]), 
													Integer.parseInt(data[5]), 
													Double.parseDouble(data[6]));
					list.add(p);
				}
				else {
					Batter b = new Batter(		Integer.parseInt(data[0]), 
												data[1], 
												Integer.parseInt(data[2]), 
												Double.parseDouble(data[3]), 
												Integer.parseInt(data[4]), 
												Integer.parseInt(data[5]), 
												Double.parseDouble(data[6]) );
					list.add(b);
				}				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void saveData(List<Human> list) {	//외부에서 값이 들어와서 쓰기
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < list.size(); i++) {
				Human h = list.get(i);
				pw.println(h.toString());
			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
