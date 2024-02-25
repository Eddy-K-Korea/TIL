# FileProc Class
```java
package file;

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
```
생성사에 파일 이름을 넣으면 인스턴스가 생성되도록 소스코드를 적어놓는다.

그리고 creatFile은 쓰기를 통해 파일을 만들 수 있지만 명시해 놓는것이 좋으므로 따로 메서드를 구현해 놓는다.

```java
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
```
외부로 배열은 내보내어 외부에서 그 배열을 읽어야 함으로 String [] 을 return값을 주는 메서드를 생성한다. 배열에 null을 초기화하고

datas[](파일의 정보를 담을 배열) 의 크기를 초기화 해주어야 한다. 따라서 count 를 통해 배열의 길이를 알아내어 배열의 크기를 정해준다.

이후 한줄씩 읽어들인 내용을 datas[]에 넣어준다.

```java
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
외부에서 값을 받고 파일로 저장을 해야하기 때문에 파라미터를 String[] datas로 설정을 한다. for문을 통해 datas[]의 크기만큼

한줄씩 넣어주고 꼭 pw.close() 를 해준다.
