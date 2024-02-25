# 자료실 구현

### 1. pom.xml 에 업로드사용할 라이브러리 추가
```xml
<!-- 파일 업로드 (자료실) -->

<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>
```  

### 2. servlet-context 에서 업로드와 다운로드시 필요한 클래스(객체) 인스턴스 생성
```xml
<!-- upload -->

<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
  <property name="maxUploadSize" value="104857600"/>
  <property name="maxInMemorySize" value="102400"/>
  <property name="defaultEncoding" value="utf-8"/>
  <property name="uploadTempDir" value="upload"/> <!--서버에 업로드할 폴더명  -->
</bean>

<!--download  -->

<bean id="downloadView" class="bit.com.spring.util.DownloadView"/>

<bean id="downloadViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver">
  <property name="order">
    <value>0</value>
  </property>
</bean>
```  

### 3. util쪽에서 업로드와 다운로드시 필요한 파일 이름변경 위한 메소드 생성
```java
package bit.com.spring.util;

import java.util.Date;

//파일명 중복에 대한 방지를 위한 util
public class PdsUtil {

	//ex:  myfile.txt -> f.indexOf('.') -> 0부터 6까지 온다
	//파일명, 확장자명
	//f.substring(6) =>  .txt 를 가져온다
	//f.substring(0,6) => myfile 을 가져온다
	
	//myfile.txt -> 23423232323.txt 로 저장 할것이다(앞에 숫자는 시스템시간)(충돌이 나지 않는다)
	//oldfilename에는  myfile.txt 를 저장하고 filename 에는 23423232323.txt를 저장한다

	public static String getNewFileName(String f) {
		String filename = "";
		String fpost = ""; //파일 확장자명
		
		if(f.indexOf('.') >=0	) { //확장자명이 있음
			fpost = f.substring(f.indexOf('.'));	// fpost = .txt가 들어가있다
			filename = new Date().getTime() + fpost; // 32423423.txt로 들어가게 된다
		}
		else {
			filename = new Date().getTime() + ".back"; //인위적으로 붙여준다
		}
		return filename;
	}
} 
```
### 4. 자료실 목록 표현, 디테일은 springsampleAll을 참고 
- 아래 다룰 내용은 업로드와 다운로드시 컨트롤에서 어떻게 구현하는지 확인 (db는 파일의 이름만을 저장하는 용도)

### 5. 업로드 controller
```java
@RequestMapping(value = "pdsupload.do",method = {RequestMethod.GET,RequestMethod.POST })
public String pdsupload(PdsDto pdsdto,
    @RequestParam(value="fileload",required=false)MultipartFile fileload,HttpServletRequest req) { //@RequestParam 뒤에 는 파일 정보를 byte형식으로 받아주는 식

  //filename 취득
  String filename = fileload.getOriginalFilename(); //원본 파일 명 myfile.text 에서 myfile
  pdsdto.setOldfilename(filename);

  //upload 경로 설정
  // 서버 톰캣에 올리는 경로 (서버가 껐다가 킬때 자주할 경우 데이터가 날라 갈 수 있다)
  String fupload = req.getServletContext().getRealPath("/upload");

  //폴더로 경로(데이터가 날아가지 않는다)
//	String fupload = "d:\\tmp"; 클라이언트 로컬에 저장

  System.out.println("fupload: "+fupload);

  //file 명을 취득
  String f = pdsdto.getOldfilename();
  String newfilename = PdsUtil.getNewFileName(f);	//return을 받게 되면 시스템 타임으로 파일명으로 받아온다

  pdsdto.setFilename(newfilename); //바뀐 파일명을 dto에 set 시켜준다

  //import java.io.File;
  File file = new File(fupload+"/"+newfilename);

  //파일을 만든다.(실제 업로드가 되는 부분) import org.apache.commons.io.FileUtils;
  try {
    FileUtils.writeByteArrayToFile(file, fileload.getBytes()); //fileload는 multipart에서 받아온것

    //db 에 저장
    service.uploadPds(pdsdto);
  } catch (IOException e) {
    e.printStackTrace();
  } 
  return "redirect:/pdslist.do";
}
```  

### 6. view에서 post로 넘겨준다 (form으로 post로 묶어서 넘겨준다)
- input 타입의 file은 post로 넘겨주어야 하며 multipart를 사용해주어야한다.
- 따라서 form의 설정에 몇가지 사항을 추가해 주어야 한다
```html
<form name="frmForm" id="_frmForm" action="pdsupload.do" method="post" enctype="multipart/form-data">
```

## 다운로드

### 7. 우선 다운로드를 위해 util에 임시 뷰를 생성한다
```java
package bit.com.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import bit.com.spring.service.PdsService;

//web에서 등록을 해야하기 때문에  생성
// 가상 뷰 다운로드 창이 만들어지는곳
public class DownloadView extends AbstractView{

	@Autowired
	PdsService service;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("DownloadView renderMergedOutputModel");
		
		File file = (File)model.get("downloadFile");	//getAttribute랑 동일하다
		
		response.setContentType(this.getContentType()); 
		response.setContentLength((int)file.length());
		
		//만약 IE/chrome
		String userAgent = request.getHeader("user-Agent");	//브라우저 헤더부분에 MSIE가 -1 보다 크면 크롬이거나 다른 브라우져이다
		boolean ie = userAgent.indexOf("MSIE") > -1;
		
		String filename = null;
		if(ie) {
			filename = URLEncoder.encode(file.getName(), "utf-8"); 
		}
		else {
			filename = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
			
		}
		// 다운로드 창
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");	//파일명이 나오기 위해
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fi = null;
		
		fi = new FileInputStream(file);
		FileCopyUtils.copy(fi, out);
		
		//download count 증가
		
		if(fi != null) {
			fi.close();
		}
	}
}
```
### 8. 다운로드 컨트롤러
```java
//fileDownload.do
@RequestMapping(value = "fileDownload.do",method = {RequestMethod.GET,RequestMethod.POST })
public String fileDownload(String filename, int seq, HttpServletRequest req, Model model) {

  //경로
  //server
  String fupload = req.getServletContext().getRealPath("/upload");

  //폴더
  // String fupload = "c:\\tmp";		만약 로컬로 하게된다면

  File downloadFile = new File(fupload + "/" + filename);

  model.addAttribute("downloadFile", downloadFile);
  model.addAttribute("seq", seq);

  return "downloadView";	//servlet context 에서 id가 downloadView으로 생성하였던 인스턴스를 찾아간다.
}
```  
