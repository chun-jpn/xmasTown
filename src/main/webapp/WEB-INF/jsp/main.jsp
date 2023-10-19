<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTLによるスクリプト要素の追加 -->
<!-- taglibディレクティブによるタグライブラリの指定 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 「prefix=」の値ははなんでもいい。基本的にはタグライブラリの接頭語を付ける。今回はcoreタグライブラリの「c」 -->
<!-- 
coreタグライブラリの中に「if」や「forEach」などのタグが登録されている 
その他のタグライブラリ
I18N:taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
Database:taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"
XML:taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"
Function:taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<c:out value="${loginUser.name}" />さん、ロングイン中
<a href="Logout">ログアウト</a>
</p>
<p><a href="Main">更新</a></p>
<form action="Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>

<!-- if文 -->
<c:if test="${not empty errorMsg }">
	<p><c:out value="${errorMsg}" /></p>
</c:if>

<!-- for文 -->
<c:forEach var="mutter" items="${mutterList}">
	<p><c:out value="${mutter.userName}" />:
		<c:out value="${mutter.text}" /></p>
</c:forEach>

<!-- これまでのjavaとは書き方が全く違う -->
</body>
</html>