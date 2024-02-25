# FileProc
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dto.Student;

public class FileProc {

	
	private File file;
	
	public FileProc(String fileName) {
		file = new File("d:\\tmp\\"+fileName+".txt");
		
	}
	
	public void createData() {
		try {
			if(file.createNewFile()) System.out.println("파일 생성 성공");
			else 					 System.out.println("파일 생성 실패");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dataSave(ArrayList<String> stu) {
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < stu.size(); i++) {
				pw.println(stu.get(i));
			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<String> dataLoad() {
		ArrayList<String> datas = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str ="";
			while((str= br.readLine())!=null) {
				datas.add(str);
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
	
}
```
