# FileProc

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	public String[] loadData() {	//외부로 값을 내보내야함
		String datas[] = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			//data의 갯수를 조사 -> 배열을 사용하기때문에    list를 사용하면 , 이 처리는 필요 없다.
			int count =0;
			String str = "";
			while((str = br.readLine())!= null) {
				count++;
			}
			br.close();
			// datas를 할당
			datas = new String [count];
			System.out.println("datas.lengh = "+ datas.length);
			
			// 배열 저장
			int w = 0;
			br = new BufferedReader(new FileReader(file));
			while((str = br.readLine()) != null) {
				datas[w] = str;
				w++;
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datas;
	}
	
	public void saveData(String[] datas) {	//외부에서 값이 들어와서 쓰기
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < datas.length; i++) {
				pw.println(datas[i]);
			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
```
