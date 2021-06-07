<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://tomcat.apache.org/example-taglib" %>
<%@ page import="com.gallery.webjava.db.entity.Exposition" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gallery.webjava.db.AdminDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>KA-TATO Administrator</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/cabinet-style.css">
    </head>
</head>
<body>
<section class="cabinet-body">
    <section class="header rov text-center text-md-start">
        <nav class="menu navbar navbar-expand-lg navbar-light ">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand" href="http://localhost:8080/gallery/">
                    KA-TATO
                    <br>
                    GALLERY
                </a>
            </div>
        </nav>
        <div class="mainButtons">
            <form id="login" action="../../logout">
                <button class="logout btn" form="login">
                    LOG OUT
                </button>
            </form>

            <form id="language" action="#">
                <button class="language btn" form="language">
                    EN
                </button>
                <button class="language btn">
                    UA
                </button>
            </form>
        </div>
    </section>
    <section class="body-content">
        <div class="cabinet-menu">
            <div class="profile-info">
                <div class="photo"></div>
                <p class="role">Administrator</p>
                <a href="#" class="profile-link">PROFILE</a>
                <a href="http://localhost:8080/gallery/admin/admin-cabinet" class="profile-link">NEW EVENT</a>
                <a href="#" class="profile-link active">STATISTICS</a>
                <a href="http://localhost:8080/gallery/admin/admin-cabinet/all-expo" class="profile-link">SEE ALL EVENTS</a>
            </div>
        </div>
        <div class="cabinet-workingplace">

            <h1>Visit Statistic</h1>
            <%
                List<Exposition> expositions = new AdminDAO().getAllExpositions();
                request.setAttribute("expositions", expositions);%>
            <table style="border: 2px solid #8F755D; width: 60%"
                   class="table table-striped">
                <tr>
                    <td><b>Exposition</b></td>
                    <td><b>TOTAL TICKETS</b></td>
                </tr>
                <c:forEach items="${expositions}" var="expo">
                    <tr>
                        <td>
                            <c:out value="${expo.getTheme()}"/>
                        </td>
                        <td>
                            <tag:visitors expoId="${expo.getId()}"></tag:visitors>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </section>


</section>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>

</html>