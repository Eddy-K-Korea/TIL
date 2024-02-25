<%@page import="dto.PdsDto"%>
<%@page import="dao.PdsDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
//upload 함수
public String processUploadFile(FileItem fileItem, String dir)throws IOException{
								// abc.txt (파일)    d:\tmp\(경로)
	String filename = fileItem.getName();		//경로 + 파일명
	long sizeInBytes = fileItem.getSize(); 	//파일 크기 얻어옴
	
	// 파일이 정상
	if(sizeInBytes > 0){
		int idx = filename.lastIndexOf("\\"); 	//d:\\tmp\\abc.txt 일때 뒤에서 부터 훝어올때 파일명 이전의 \\의 인덱스
		if(idx == -1){	//만약 파일이 d:/tmp/abc.txt 이렇게 되어있으면 위의 방법으로 찾을수 없다
			idx = filename.lastIndexOf("/");
		}
		
		filename = filename.substring(idx+1); // 파일명을 전체를 읽어오도록함 \\ , / 다음 인덱스부터 끝까지 가져온다.
		
		File uploadFile = new File(dir,filename);	//경로에 새로운 파일 생성
		
		try{
			fileItem.write(uploadFile);	//실제 upload 부분
		}catch(Exception e){}
	}
	return filename; //DB에 저장하기 위한 return
}


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
//tomcat 배포(server)
String fupload = application.getRealPath("/upload"); // 서버를 끄게 되며내용이 날아갈 수 있다.
//지정 폴더(client)
// String fupload = "d:\\tmp";


System.out.println("업로드 폴더:"+fupload);


String yourTempDir = fupload;

int yourMaxRequestSize = 100*1024*1024; 	// 1Mbyte
int yourMaxMemorySize = 100*1024; //1kbyte

//form field의 데이터를 저장할 변수
String id = "";
String title = "";
String content = "";

//file
String filename= "";

boolean isMultipart = ServletFileUpload.isMultipartContent(request);
if(isMultipart == true){
	//Fileitem 생성
	DiskFileItemFactory factory = new DiskFileItemFactory();		// 구분
	
	factory.setSizeThreshold(yourMaxMemorySize);
	factory.setRepository(new File(yourTempDir));
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(yourMaxRequestSize);
	
	List<FileItem> items = upload.parseRequest(request); //form으로 넘겨준 값들을 리스트로 넘겨줌
	Iterator<FileItem> it = items.iterator();
	
	//구분
	while(it.hasNext()){
		FileItem item = it.next();
		
		if(item.isFormField()){			//id, title, content
			if(item.getFieldName().equals("id")){
				id = item.getString("utf-8");	//위에 선언한 변수에 값들을 넣어줌
			}
			else if(item.getFieldName().equals("title")){
				title = item.getString("utf-8");
			}
			else if(item.getFieldName().equals("content")){
				content = item.getString("utf-8");
			}
		}
		else{	//fileload
			if(item.getFieldName().equals("fileload")){
				filename = processUploadFile(item, fupload);
			}
			
		}
		
	}
}


// DB에 저장
PdsDao dao = PdsDao.getInstance();
boolean isS = dao.writePds(new PdsDto(id,title,content,filename));
if(isS){
	%>
	<script type="text/javascript">
		alert("파일 업로드 성공");
		location.href = "pdslist.jsp";
	</script>
		
	<%
}else{
	%>
	<script type="text/javascript">
	alert("파일 업로드 실패");
	location.href = "pdslist.jsp";
	</script>
	
	<%
}

%>




</body>
</html>