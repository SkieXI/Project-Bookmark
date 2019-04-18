<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html lang="en">
<spring:url value="/resources/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<body>
	<ul>
		<li><a href="home">Home</a></li>
		<li><a href="login">Login</a></li>
		<li><a href="register">Register</a></li>
		<li><a href="addProduct">Add Product</a></li>
	</ul>
	<h2>List of Products</h2>
	<table>
		<tr>
			<th><label>Title</label></th>
			<th><label>Author</label></th>
			<th><label>Published On</label></th>
			<th><label>Total Pages</label></th>
			<th><label>Pages Read</label></th>
			<th><label>Start Date</label></th>
			<th><label>Finished?</label></th>
			<th><label>Edit</label></th>

		</tr>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><label>${book.title}</label></td>
				<td><label>${book.author}</label></td>
				<td><label>${book.publishDate}</label></td>
				<td><label>${book.pagesTotal}</label></td>
				<td><label>${book.pagesRead}</label></td>
				<td><label>${book.startDate}</label></td>
				<td><c:choose>
						<c:when test="${book.finishedCheck == 0}">
							<label>NO</label>
						</c:when>
						<c:when test="${user.finishedCheck == 1}">
							<label>YES</label>
						</c:when>
						<c:otherwise>
							<label>Unknown</label>
						</c:otherwise>
					</c:choose></td>
				<td colspan="2"><form:form method="POST" modelAttribute="book"
						action="editProduct">
						<button type="submit" class="btn btn-primary">edit
							product</button>
					</form:form></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">
	</table>
	<form:form method="POST" modelAttribute="book" action="displayRefresh">
		<input type="submit" value="refresh" />
	</form:form>
</body>