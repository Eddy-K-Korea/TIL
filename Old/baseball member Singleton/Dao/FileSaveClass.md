# (Dao) FileSaveClass
- 파일 세이브는 Dao에 넣어주는것이 효율적이다. 

```java
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import DaoInterface.DaoImpl;
import singleton.SingletonCls;

public class FileSaveClass implements DaoImpl {

	File file = new File("d:\\tmp\\baseball.txt");
	
	public FileSaveClass() {
	}
	
	@Override
	public void process() {
		SingletonCls sc = SingletonCls.getInstance();
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < sc.list.size(); i++) {
				pw.println(sc.list.get(i).toString());
			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("파일에 저장되었습니다.");
	}
}
```

