<%@page import="org.jsoup.Jsoup"%>
<%@page import="tPCertificatifApp.service.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deck creation</title>
</head>

<%!
	
	
%>

<body>
 <form action="ControllerTpCertificatif?action=createDeck" method="POST" name="createDeck" >
 	 	<label>Word Language : </label>
 	 	<select>
 		<option value="Language.FRENCH">French</option>
 		<option value="Language.ENGLISH" >English</option>
 		<option value="Language.SPANISH">Spanish</option>
 	</select>
 		<br>
 		<label>Word Translation Language : </label>
 	 	<select>
 		<option value="Language.FRENCH">French</option>
 		<option value="Language.ENGLISH" >English</option>
 		<option value="Language.SPANISH">Spanish</option>
 	</select>
 	
 	
 	<br>
 	<label>Word 1 : </label> <input type="text" name="newWord"> <br/>
 	<label>Word 2 : </label> <input type="text" name ="newWord"><br/>
 	<label>Word 3 : </label> <input type="text" name ="newWord">  <br/>

 	<input type="button" name="CreateDeck" value="Create Deck">
 </form>
</body>
</html>