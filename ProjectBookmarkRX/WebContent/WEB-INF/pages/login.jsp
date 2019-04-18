<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
            <li class="nav-item"><a class="nav-link" href="home">Home <span class="sr-only">(current)</span> </a></li>
            <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
        </ul>
    </div>
</nav>
	<h4>Login</h4>
		<div class="container" align="center">
	<form:form class="form-horizontal" method="POST" modelAttribute="user" action="loginUser">
		<table class="table table-responsive">
			<tr>
				<div class="form-group">
				<td><form:label path="email">Email</form:label></td>
				<div class="col-xs-9">
				<td><form:input path="email" /></td>
				</div>
				</div>
			</tr>
			<tr>
			<div class="form-group">
				<td><form:label path="password">Password</form:label></td>
				<div class="col-xs-9">
				<td><form:input path="password" /></td>
				</div>
				</div>
			</tr>
			<tr>
			<div class="col-xs-9">
				<td colspan="2"><input type="submit" value="Submit" /></td>
				</div>
			</tr>
		</table>
		<br />
		<form:errors path="email" />
		<br />
		<form:errors path="password" />
		<br />
	</form:form>
		<div class="container" align="center">