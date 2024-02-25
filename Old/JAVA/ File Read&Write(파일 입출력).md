## File Read & Write

### 1. Write 

파일에 입력을 위해 사용하는 함수이다. 

```
File file = new File("d:/temp/test.txt");

```

우선 해당 파일의 경로에 파일 및 file 객체를 생성합니다.

```
FileWriter fw = new FileWriter(file);
		
fw.write("안녕하세요"+ "\n");
fw.close();
```

FileWriter 클래스의 객체를 생성하고 위의 소스코드처럼 입력이 가능하다. 단. 해당 방법은 문자 하나하나씩 입력하는 방법이다.

```
FileWriter fWriter = new FileWriter(file);		    //파일 포인터 설정
BufferedWriter bw = new BufferedWriter(fWriter);	//한글자씩들을 한문장으로 바꿔줌
PrintWriter pw = new PrintWriter(bw);
		
		pw.print("안녕"+"\n");
		pw.println("하이하이");
		pw.println("건강하세요.");
		
		pw.close();    // 굉장히 중요 ★  무조건 닫아줘야함. 안그러면 저장이 안됨
```

위의 버퍼스트림을 이용하여 문자열을 입력하는 방법이 주로 사용된다. 또한 printWriter의 클래스를 이용해서

파일에 입력시 println 의 기능도 사용 할 수 있다. 그리고 입력이 완료 되었다면 반드시 close를 통해 닫아주어야 한다.



### 2. Read

저장한 파일을 읽기위해 사용한다.

```
File file = new File("d:/temp/test.txt");
```
같은 방법법으로 읽을 파일의 경로와 객체를 생성한다.

```
FileReader fr = new FileReader(file);
```
FileReader를 통해 아까 경로의 파일을 읽기위해 준비한다.

```
String str = "";	//문자열로 받기
			//한문자씩 읽어 들인다.
int c = fr.read();
while(c != -1) {		// -1 == 파일의 끝
  System.out.println((char)c);
  str = str + (char)c;
  c = fr.read();
}
System.out.println(str);
```

파일의 문자를 하나씩 읽어 문자로 변환 후 문자열 변수에 넣어 합해주어 확인이 가능하다.

```
FileReader fr = new FileReader(file);
BufferedReader br = new BufferedReader(fr);
String str;
			while((str = br.readLine()) != null) {
				System.out.println(str);
			//	str = br.readLine();
			}
				
			br.close();
```

쓰기와 마찬가지로 읽기도 문자열로 읽기 위해 버퍼클래스를 이용한다. while구문의 조건을 보면 

str = br.readLine() 으로 되어있다. 파일의 한줄을 str문자열로 넣어준다는 의미이다.

먄약 String str = br.readLine()을 while문 밖에 따로 선언하고 while(str != null) 을 구현하고

while 처리 내용에 str = br,.readLine()을 넣어준다면 같은 구현방법이다.



##### ※ 이때 둘다 예외가 발생하기 때문에 main 함수에 throws Exception 을 걸어두었다.


### 파일 읽고 쓰기 함수 구현하기
1. wirte<br>
String arrAtr[] = {
"Hello",
"안녕하세요",
	"Hi"
};
파일명.txt 저장되는 함수 생성
		 
		 
2. read<br>
String strArr[]; <br>
[0] <- "hello"
[1] <- "안녕하세요"
[2] <- "Hi"
		 		
함수로 구현

1. Write
```
static boolean dataSave(File f, String datas[]) {
  FileWriter fwriter;
  try {
    fwriter = new FileWriter(f);
    BufferedWriter bw = new BufferedWriter(fwriter);
    PrintWriter pw = new PrintWriter(bw);
					
    for (int i = 0; i < datas.length; i++) {
    pw.println(datas[i]);
    }
    pw.close();
  } catch (IOException e) {
    return false;
  }
    return true;
}
```
boolean 형으로 return값을 받는 함수이다. 파일의 주소를 받아야하기 때문에 인수에는 File f 와 입력하고 싶은 문자열이 담겨있는 배열

을 인수로 받는다.  FileWriter를 생성해주고 버퍼와 printWriter까지 생성한다. 또한 내가 입력할 문자열인 배열을 for문을 돌려서

pw.println에 넣는다. 예외처리를 해주고 예외로 처리가 되면 false로 return값을 주고 정상 쓰기 되었다면 true 를 준다.

```

public static void main(String[] args){

File file = new File("d:\\temp\\work1.txt");
		String arrStr[] = {"hello world","안녕하세요","Hi mr","하이하이","금요일"};
		
		boolean b = dataSave(file, arrStr);
		if(b) {
			System.out.println("성공적으로 파일에 데이터가 저장되었습니다.");
		}else {
			System.out.println("파일에 데이터가 저장되지 않았습니다.");
		}
}
```

위의 쓰기 소스코드를 통해 메인 함수에서 예외처리되면 "파일에 데이터가 저장되지 않았습니다." 정상 쓰기되면 

"성공적으로 파일에 데이터가 저장되었습니다." 가 출력 되도록 하였다.

2. Read

```
static String[] dataLoad(File f) {
		String str[] = null;
		try {
			FileReader fr = new FileReader(f);
			// 데이터를 카운트(몇개?
			int count = 0;
			String s;
			BufferedReader br = new BufferedReader(fr);
			while((s=br.readLine())!=null) {
				count++;
			}
			br.close(); // 커서(버퍼리더)를 다시 맨 처음으로 돌리기위해 한번 끝까지간 커서를 닫아준다.
```
우리가 배열을 이용하여 구현을 하기위해 배열의 길이를 카운트 해주는 소스를 해주어야한다. while 문을 통해 문자열이

얼마나 있는지 측정하고 count로 잡아 놓는다. 커서(버퍼리더)를 다시 맨 처음으로 돌리기위해 한번 끝까지간 커서를 br.close 를 통해 닫아준다.

```
			// 배열 셋팅
			str = new String [count];
			// 읽고 데이터 저장
			int i = 0;
			fr = new FileReader(f);
			br = new BufferedReader(fr);	//다시 버퍼를 열어서 읽기 시도
			while((s = br.readLine()) != null) {
				str[i] =s;
				i++;
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
```
count를 알았고 셋팅해준 다음 다시한번 파일을 읽어준다. 그 후 배열에다가 문자열을 차례로 대입해준다.

```
public static void main(String[] args){

String arrayStr[] = dataLoad(file);
		for (int i = 0; i < arrayStr.length; i++) {
			System.out.println(arrayStr[i]);
		}
}    
```

메인에서 dataLoad 함수를 통해 파일의 문자열을 읽고 배열의 값을 얻고나서 for문을 통해 배열을 출력해준다.
