<%@page import="TPCertificatifApp.util.IPersisanceService"%>
<%@page import="TPCertificatifApp.util.DAOUtil"%>
<%@page import="TPCertificatifApp.service.*"%>
<%@page import="TPCertificatifApp.bean.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top 10 users</title>
</head>
<body>
	<%
		UserDao userDao = new UserDao();
		UserPersistanceService userPersistance = new UserPersistanceService();
		List<AbstractUser> userList = userPersistance.loadElement(IPersisanceService.USER_SAVE_LOCATION+"users.xml");
		userDao.showLeaderBord(userList);
	%>
</body>
</html>