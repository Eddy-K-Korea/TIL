# file 업로드 & 서버에서 불러오기

###  이전 자료실과 비슷한 내용이지만 세미 프로젝트를 하면서 이미지파일을 서버 파일에 올려두고 경로를 접근하여 불러오는 내용을 기록

## 파일 업로드 하는 부분

```html
<form action="addPro" method="post" enctype="multipart/form-data">  <-- 보내주는 방식이 중요 -->
	<ul>
		<li>제품명<br><input type="text" name="p_name"></li>
		<li>원단(재료)<br><input type="text" name="p_material"></li>
		<li>제조 국가<br><input type="text" name="p_madeIn"></li>
		<li>가격<br><input type="number" name="price"></li>
		<li>카테고리 분류<br>
			<select id="selbig" name="selbig">
				<option>대분류</option>
				<option value="top">상의</option>
				<option value="bottom">하의</option>
				<option value="shoes">신발</option>
				<option value="accessary">악세사리</option>
			</select>
			<select id="selsmall" name="selsmall">
				<option>소분류</option>
			</select>
		</li>
		<li>이미지 파일<br><input type="file" name="fileload"></li>
		<li>제품 정보<br><textarea rows="10" cols="100" name="p_info"></textarea></li>
	</ul>
	<input type="submit" value="제품 추가">
	</form>
```

- form 으로 감싼 내용들을 보내는 방식으로 진행하였다. 여기서 input type="file" 방식이 파일을 올리고 보내는 부분
- form의 보내는 방식 중 multipart/form-data로 보내야 파일까지 같이 보내진다. 


### Servlet 으로 보내준다

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		ProductDetailDao dao = ProductDetailDao.getInstance();
		
	
			String savePath = req.getServletContext().getRealPath("/productimg");   // 실제 파일이 저장될 경로를 잡아준다
			System.out.println(savePath);
			int sizeLimit = 1024*1024*15;     //저장될 파일의 크기 제한을 잡아준다
			String encoding = "UTF-8";        // 저장될 파일의 인코딩 설정
			
      //   아까 form으로 multipart로 보내주는것을 받아준다 
      //                                           request, 경로, 파일 크기, 인코딩설정, 중복파일 방지 기능
			MultipartRequest multi = new MultipartRequest(req, savePath, sizeLimit, encoding, new DefaultFileRenamePolicy());		
			
			
			String name = multi.getParameter("p_name");
			String mertiral = multi.getParameter("p_material");
			String madeIn = multi.getParameter("p_madeIn");
			int price = Integer.parseInt(multi.getParameter("price"));
			String category = multi.getParameter("selbig");
			String kind = multi.getParameter("selsmall");
			int p_kind = Integer.parseInt(kind);
			String info = multi.getParameter("p_info");
			
			String filename = multi.getFilesystemName("fileload");  // 파일의 이름만 받아준다.
			
			String fileFullPath = savePath + "/" + filename;
			
			System.out.println(name+" "+mertiral+" "+madeIn+" "+price+" "+category+" "+kind+" "+fileFullPath+" "+info+" ");
			
			ProductDto dto = new ProductDto(name, info, mertiral, madeIn, price, category, p_kind, filename); // 받은 값들을 객체에 넣어준다(이미지 파일과 묶기위해)
			boolean isS = dao.insertProduct(dto); // DB에 넣어줌
			req.setAttribute("insert", isS);
		
			forward("addPAF.jsp", req, resp);
		
	}
	public void forward(String linkname, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher = req.getRequestDispatcher(linkname);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```  

- 파일의 이름을 객체 (dto)에 넣어주고 파일을 불러올때 DB에 접근하여 파일 이미지 이름 값만 추출하면 된다

### 등록 여부 확인
```java
boolean isS = (boolean)request.getAttribute("insert");
if(isS){
	%>
	<script type="text/javascript">
		alert("등록되었습니다");
		location.href="addPro?work=list";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
		alert("등록 실패");
		location.href="addProduct.jsp";
	</script>	
	<%
}
```
- 확인 후 다시 컨트롤러에 보내준다.(이때 기본값인 get 방식으로 값들을 넘겨준다)

### 다시 servlet
```java
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		ProductDetailDao dao = ProductDetailDao.getInstance();
		List<ProductDto> list = new ArrayList<ProductDto>();
		if(work.equals("list")) {
			list = dao.getProductList("all");
			req.setAttribute("list", list);
			forward("productList.jsp", req, resp);
		}
	}
```
- 리스트에 아까 값들을 받은 객체들을 넣어준다. 그리고 서버에 저장된 이미지를 뿌려주기 위해 다시 view로 간다.

### 이미지 list view
```java
<%
List<ProductDto> list = (List<ProductDto>)request.getAttribute("list");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>제품</h1>
<%
for(int i = 0; i < list.size(); i++){
	ProductDto pro = list.get(i);
	%>
		<a href="productDetail?work=product&seq=<%=pro.getSeq() %>">
		<img alt="이미지 없음" src="productimg/<%=pro.getFilename()%>" width="300px" height="300px"></a>
	<%
}
%>
```
- <img > 태그에 src 경로를 아까 저장 경로로 설정하였던 곳으로 지정하고 파일 이름을 db에 접근하여 불러온다.
