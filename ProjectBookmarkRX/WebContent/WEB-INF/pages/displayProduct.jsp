<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<spring:url value="/resources/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script type="text/javascript">
	//All of this was from Mark Moot as we were panicking at the last minute and we were hitting our heads for at least 3 hours.
	function getBooks() {
		$.ajax({
			type : "GET",
			url : "/ProjectBookmarkRX/service1/books",
			dataType : "json",
			success : function(data) {

				//Display
				$('#books').dataTable({
					"responsive" : true,
					"data" : data,
					"columns" : [ {
						"data" : "title",
						"reposonsivePriority" : 2
					}, {
						"data" : "author",
						"responsivePriority" : 3
					}, {
						"data" : "publishDate",
						"reposonsivePriority" : 8
					}, {
						"data" : "pagesTotal",
						"responsivePriority" : 5
					}, {
						"data" : "pagesRead",
						"reposonsivePriority" : 4
					}, {
						"data" : "startDate",
						"reposonsivePriority" : 6
					}, {
						"data" : "lastDate",
						"responsivePriority" : 7
					}, {
						"data" : "finishedCheck",
						"responsivePriority" : 9
					}, {
						"data" : "bookID",
						"responsivePriority" : 1, "render" : function(data, type, full, meta) {
							return '<a href="/ProjectBookmarkRX/user/editProduct/' + data + '">Edit Book</a>';
						}
					} ]
				});
			},

			error : function(xhr, ajaxOptions, throwError) {
				alert(xhr.status);
				alert(throwError);
			},
		})
	}
	$(document).ready(getBooks);
</script>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand">Navigation</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
        data-target="#navbarColor02" aria-controls="navbarColor02"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="home">logout <span class="sr-only">(current)</span> </a></li>
            <li class="nav-item"><a class="nav-link" href="displayRefresh">Main Menu</a></li>
            <li class="nav-item"><a class="nav-link" href="addProduct">Add a Book</a></li>
        </ul>
    </div>
</nav>



<table id="books" width="75%">
	<thead>
		<tr>
			<th><label>Title</label></th>
			<th><label>Author</label></th>
			<th><label>Published Date</label></th>
			<th><label>Total Pages</label></th>
			<th><label>Pages Read</label></th>
			<th><label>Start Date</label></th>
			<th><label>Last Date</label></th>
			<th><label>Finished?</label></th>
			<th><label>Id</label></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
	<form:form method="GET" modelAttribute="book" action="addProduct">
		<input type="submit" value="Add book." />
	</form:form>