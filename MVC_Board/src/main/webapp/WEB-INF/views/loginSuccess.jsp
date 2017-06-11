<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		String aa = null;
		if (session.getAttribute("userId") != null) {
			aa = (String) session.getAttribute("userId");
			
		}
	%>
	로그인 성공
<%=aa %>
</body>
</html>