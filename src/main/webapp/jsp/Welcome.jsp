<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteUser(id){
		location.href = "/FP/DeleteUser.do?id=" + id;
	}
	function editUser(id){
		alert(id);
		var xmlhttp;
		var url = "http://localhost:8080/FP/FindEditingUser.do?id=" + id;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var str = "<table>";
				str = "<tr><td>User Id:</td> <td><input type='text' name='editUserId' id='editUserId' readonly='readonly' value=''></td></tr>Non-Editable";
				str += "<tr><td>User Name:</td> <td><input type='text' name='editUserName' id='editUserName' value=''></td></tr>";
				str += "<tr><td>User Role:</td> <td>  <input type='text' name='editUserRole' id='editUserRole' value=''></td></tr>";
				str += "<tr><td>User Rating:</td> <td>  <input type='text' name='editUserRating' id='editUserRating' value=''></td></tr></table><br>";
				str += "<input type='submit' value='Update' onclick='getUserValues()'>";
				var array = new Array();
				array = xmlhttp.responseText.split(",");
				
				document.getElementById("editingUser").innerHTML = str;
				document.getElementById("editUserId").value = array[0];
				document.getElementById("editUserName").value = array[1];
				document.getElementById("editUserRole").value = array[2];
				document.getElementById("editUserRating").value = array[2];
			}
		};
		xmlhttp.open("Get", url, true);
		xmlhttp.send();
	}
	function getUserValues(){
		alert("called");
		var userId = document.getElementById("editUserId").value;
		var userName = document.getElementById("editUserName").value;
		var userRole = document.getElementById("editUserRole").value;
		var userRating = document.getElementById("editUserRating").value;
		location.href = "/FP/EditUser.do?editUserId=" + userId + "&editUserName=" + userName + "&editUserRole=" + userRole + "&editUserRating=" + userRating;
	}
	function copyUser(id){
		location.href = "/FP/CopyUser.do?id=" + id;
	}
</script>
</head>
<body>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Role</td>
			<td>Ratings</td>
			<td colspan="3">Action</td>
		</tr >
		<c:forEach items="${requestScope.users }" var="user"
			varStatus="status">
			<tr >
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td><c:if test="${user.roleId eq '1'}">Admin</c:if> <c:if
						test="${user.roleId eq '2'}">Moderator</c:if> <c:if
						test="${user.roleId eq '3'}">User</c:if></td>
				<td>
					<c:forEach begin="1" end="${user.ratings }" varStatus="loop">
						<span style="color: gold">*</span>
					</c:forEach>
					<c:forEach begin="0" end="${5 - user.ratings }">
						<span style="color: grey">*</span>
					</c:forEach>
				</td>
				<td><img alt="Delete one user!" src="./images/delete.jpg"
					onclick="deleteUser(${user.id })" style="height: 10; width: 10px;"
					id="${user.id }"></td>
				<td><img alt="Edit one user!" src="./images/edit.jpg"
					onclick="editUser(${user.id })" style="height: 10; width: 10px;"
					id="${user.id }"></td>
				<td><img alt="Copy one user!" src="./images/copy.jpg"
					onclick="copyUser(${user.id })" style="height: 10; width: 10px;"
					id="${user.id }"></td>
			</tr>
		</c:forEach>
	</table>
	<div id="editingUser"></div>
	<input type="hidden" id="editingUserId">
	<input type="hidden" id="editUserName">
	<input type="hidden" id="editUserRole">
	<input type="hidden" id="editUserRating">
</body>
</html>