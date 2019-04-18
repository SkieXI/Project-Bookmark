<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand">Navigation</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
        data-target="#navbarColor02" aria-controls="navbarColor02"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
        <li class="nav-item"><a class="nav-link" href="../home">logout <span class="sr-only">(current)</span> </a></li>
        <li class="nav-item"><a class="nav-link" href="../displayRefresh">Main Menu</a></li>
        <li class="nav-item"><a class="nav-link" href="../addProduct">Add Book</a></li>
        </ul>
    </div>
</nav>
<!-- when adding in navbar, make sure all methods are ../FUNCTION -->
	<h2>Edit Book</h2>
		<div class="container" align="center">
	<form:form method="POST" modelAttribute="book" action="../updateProduct">
		<table class="table table-responsive" align ="left">
			<tr>
				<div class="form-group">
				<td><form:label path="title">Title</form:label></td>
				<div class="col-xs-9">
				<td><form:input path="title" /></td>
				</div>
				</div>
			</tr>
			<tr>
				<div class="form-group">
				<td><form:label path="author">Author</form:label></td>
				<div class="col-xs-9">
				<td><form:input path="author" /></td>
				</div>
				</div>
			</tr>
			<tr>
				<div class="form-group">
				<td><form:label path="publishDate">Date Published</form:label></td>
				<div class="col-xs-9">
				<td><form:input path="publishDate" /></td>
				</div>
				</div>
			</tr>
			<tr>
				<td><form:label path="pagesTotal">Pages</form:label></td>
				<div class="col-xs-9">
					<td><form:input path="pagesTotal" /></td>
			</tr>
			<tr>
				<div class="form-group">
					<td><form:label path="startDate">Date started reading</form:label></td>
					<div class="col-xs-9">
						<td><form:input path="startDate" /></td>
					</div>
				</div>
			</tr>
			<tr>
				    <td><form:input type="hidden" path="bookID"/><td>
					<!--<td><form:label path="bookID">BookID</form:label></td>
					<td><form:input path="bookID" /></td>-->
				</tr>
			<tr>
				<div class="form-group">
					<td colspan="2">
					<input type="submit" value="Submit" /></td>
					<div class="col-xs-9">
			</tr>
		</table>
		<br />
		<form:errors path="*" />
	</form:form>

	<form:form method="POST" modelAttribute="book" action="../deleteProduct">
	<form:input type="hidden" path="bookID"/>
		<input type="submit" value="Remove Book." />
	</form:form>
</div>