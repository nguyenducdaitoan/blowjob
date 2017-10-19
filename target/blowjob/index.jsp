<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <title>Employees</title>
</head>
<body>

<nav class="light-teal-team2 lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="#" class="brand-logo">DIR BI TRAINING</a>
    </div>
</nav>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br>
        <div class="row">
            <form:form method="POST" commandName="csvFile" action="upload" enctype="multipart/form-data" id="form" onsubmit="myFunction()">
                <input required type="file" name="file" class="filestyle" data-icon="false"  id = "file"/>
                <input type="submit" class="btn btn-primary btn-plus" value="Import" id ="submitBtn" >
            </form:form>
        </div>
        <br>
    </div>
</div>

 <!-- PRELOADER -->
  <div class="container" id="progress" style="visibility: hidden">
    <div class="section">
      <div class="progress">
        <div class="indeterminate"></div>
      </div>
    </div>
  </div>

<%--<div class="container">--%>
    <%--<div class="section">--%>
        <%--<div class="row">--%>
            <%--<h5 class="left red-text">エラーメッセージ</h5>--%>
        <%--</div>--%>
        <%--<div class="card-panel">--%>
            <%--<span class="red-text text-darken-2">メッセージの内容メッセージの内容メッセージの内容メッセージの内容メッセージの内容メッセージの内容メッセージの内容メッセージの内容</span>--%>
        <%--</div>--%>
        <%--<hr>--%>
    <%--</div>--%>
<%--</div>--%>

<c:if test="${pagingProductList.size() > 0}">
<!-- PAGING -->
<div class="container">
    <div class="section">
        <div class="right">
            <ul class="pagination">
                <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a href="/listProduct?page=${currentPage - 1}">Previous</a></td>
                    <li class="disabled"><i class="material-icons">chevron_left</i></li>
                </c:if>
                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                        <li class="active">${i}</li>
                        </c:when>
                        <c:otherwise>
                        <li class="waves-effect"><a href="listProduct?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <%--For displaying Next link --%>
                <c:if test="${currentPage < noOfPages}">
                    <li class="waves-effect"><i class="material-icons">chevron_right</i></li>
                    <td><a href="/listProduct?page=${currentPage + 1}">Next</a></td>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<br><br>

<!-- PRODUCT LIST -->
<div class="container">
    <div class="section">
        <h4 class="teal-text text-darken-2">Product List</h4>
        <hr/>
        <div class="row">
            <table class="bordered highlight responsive-table">
                <thead>
                <tr>
                    <td>Product Code</td>
                    <td>Last Name</td>
                    <td>First Name</td>
                    <td>Sex</td>
                    <td>Date Of Birth</td>
                    <td>Phone Number</td>
                    <td>image2</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${pagingProductList}">
                    <tr>
                        <td>${product.productCode}</td>
                        <td>${product.branchName}</td>
                        <td>${product.title}</td>
                        <c:choose>
                            <c:when test="${product.price == 1}">
                            <td>Male</td>
                            </c:when>
                            <c:otherwise>
                            <td>Female</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${product.saleRank}</td>
                        <td>${product.image1}</td>
                        <td>${product.image2}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</c:if>
<script type="text/javascript">
function myFunction() {
    /* alert('A success'); */
    document.getElementById("submitBtn").disabled = true;
    document.getElementById("progress").style.visibility="visible"; 

    $.ajax({
        type: "POST",
        dataType: formdata,
        url: "${home}/upload",
        data: $("#form").serialize(), //serializes the form's elements.
        success: function(data)
        {
            alert('A success');// show response from the php script.
        }
      });

     e.preventDefault(); // avoid to execute the actual submit of the form.
}
</script>
</body>
</html>
