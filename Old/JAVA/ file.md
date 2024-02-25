## File

code -> logic (구현)
        기능적: file, database
        
저장매체 : 파일, DB 에 저장 <br>
				  이름  	나이 		주소<br>
			       홍길동	24		서울시	-> write, read        
             
- 목적: 데이터의 저장, 불러오기  
- 종류 : *.txt(최상위 파일 형식) - jpg, dll, lib, jar , png 
		
		.dll(Dynamic Link Library)  -> 동적 파일  (필요한 메모리만 사용 메모리 절약에 좋음)
		.lib 				-> 정적(static)
		 	-> 자바의 lib 가 jar 이다.

### 1. 파일 목록

```
File fdir = new File("c:\\");
		
String filelist[] = fdir.list();
  for (int i = 0; i < filelist.length; i++) {
  System.out.println(filelist[i]);
```

File 클래스를 사용하여 fdir객체를 생성한다. 매개변수로는 파일의 주소가 들어간다.

문자열 배열의 filelist[] 에 fdir리스트를 넣어준다.

### 2. 파일, 폴더, 그외 검색하기

```
File fdir = new File("c:\\");
		
File filelist[] = fdir.listFiles();
		
for (int i = 0; i < filelist.length; i++) {
  if(filelist[i].isFile()) {
    System.out.println("[파일]"+ filelist[i].getName());
  }
  else if(filelist[i].isDirectory()) {
    System.out.println("[폴더]"+ filelist[i].getName());
  }
  else {
    System.out.println("[?]"+filelist[i].getName());
  }
}
```

 File filelist[] 파일배열로 fdir위치의 파일 리스트를 저장한다.
 
 배열을 for문으로 돌리고 파일이면 isFile()함수를 이용하여 찾아내고 폴더이면 isDirectory() 로 getName을 통해 
 
 출력한다.
 
 ### 3. 파일 생성
 
 ```
 
 String filestr = "d:\\temp\\newfile.txt";
		
File newfile = new File(filestr);
		
try {
  if(newfile.createNewFile()) {
    System.out.println("파일 생성 성공!");
  }else {
    System.out.println("파일 생성 실패");	// 같은 파일명이 있을경우 생성 실패
  }
}catch(IOException e) {
  e.printStackTrace();
}
 ```
 
 new를 통해 File클래스를 이용하기 위한 객체를 생성한다. 여기서 예외를 걸어주는 이유는 파일을 새로만들 경로가 없을 수 있기 때문에 
 
 예외처리해준다. createNewFile() 의 함수를 통해 새 파일을 생성한다.
 
 ### 4. 파일 존재 확인
 
 ```
if(newfile.exists()) {
  System.out.println("파일이 존재합니다.");
}else {
  System.out.println("파일이 존재하지 않습니다.");
}
 
 ```
 exists를 이용하여 생성된 파일이 있는지 확인이 가능하다.
 
 ### 5. 파일 삭제
 
 ```
if(newfile.delete()) {
  System.out.println("파일을 삭제 하였습니다.");
}else {
  System.out.println("파일을 삭제하지 못 했습니다.");
}
 ```
 생성된 객체에 delete() 함수를 이용하여 삭제할 수 있다.
 
 ### 6. 파일 읽기 여부 파악 및 읽기 전용 설정
 
 ```
if(newfile.canRead()) {
  System.out.println("파일을 읽을 수 있습니다.");
}else {
  System.out.println("파일을 읽을 수 없습니다.");
}

newfile.setReadOnly();
 ```
 canRead()는 해당 파일을 읽을 수 있는지 확인 할 수 있다. 또한setReadOnly() 함수는 해당 파일을 읽기전용으로 변경해준다.
 
 ### 7. 쓰기 가능여부
 
 ```
if(newfile.canWrite()) {
  System.out.println("파일을 쓰기가 가능 합니다.");
}else {
  System.out.println("파일 쓰기가 불가능 합니다.");
}
 
 ```
 canWrite() 는 쓰기 가능한지 확인한다. 아까 setReadOnly()를 설정해 놓았기 때문에 newfile은 쓰기 불가능 멘트가 출력된다.
