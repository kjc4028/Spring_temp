<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><td colspan="3" align="center">회원 명단</td></tr>
<tr><td>아이디</td><td>비밀번호</td><td>이름</td></tr>

<c:forEach items="${memberlist}" var = "a">
<tr><td>${a.id}</td><td>${a.password}</td><td>${a.name}</td>
</c:forEach>
</table>
	
</body>
</html>