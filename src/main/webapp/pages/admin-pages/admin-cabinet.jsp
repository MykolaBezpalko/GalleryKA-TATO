<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gallery.webjava.db.AdminDAO" %>
<%@ page import="com.gallery.webjava.db.entity.Hall" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="../pages/styles/cabinet-style.css">
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
                <a class="navbar-brand" href="#">
                    KA-TATO
                    <br>
                    GALLERY
                </a>

            </div>
        </nav>
        <div class="mainButtons">
            <form id="login" action="../logout">
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
                <a href="#" class="profile-link active">NEW EVENT</a>
                <a href="#" class="profile-link">STATISTICS</a>
                <a href="../pages/admin-pages/admin-cabinet-allexpo.jsp" class="profile-link">SEE ALL EVENTS</a>

            </div>
        </div>
        <div class="cabinet-workingplace">

            <h1>Create A New Event</h1>

            <form action="${pageContext.request.contextPath}/create-event" id="create-expo" method="POST">
                <div class="top">
                    <div class="left">
                        <div class="item">
                            <p class="title-item">THEME
                            </p>
                            <input type="text" name="theme" placeholder="Insert Theme" form="create-expo" required>
                        </div>
                        <div class="item">
                            <p class="title-item">HALL</p>

                            <div class="dropdown">
                                <input class="btn btn-secondary dropdown-toggle halls" type="button"
                                       value="Choose halls"
                                       id="dropdownMenuButton1"
                                       data-bs-toggle="dropdown" aria-expanded="false"
                                       placeholder="Choose halls"/>
                                <%
                                    List<Hall> halls = new AdminDAO().getAllHalls();
                                    request.setAttribute("halls", halls);%>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

                                    <c:forEach items="${halls}" var="hall">
                                        <li>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox"
                                                       name="hall" value="${hall.getHallName()}"
                                                       id="flexCheckDefault">

                                                <label class="form-check-label" for="flexCheckDefault">
                                                    <c:out value="${hall.getHallName()}"/>
                                                </label>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>


                        </div>


                        <div class="item">
                            <%
                                Date d = Calendar.getInstance().getTime();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                session.setAttribute("today", format.format(d));
                            %>

                            <label for="start-date">Begin date</label>
                            <input id="start-date" type="date" name="start-date"
                                   value="<%=session.getAttribute("today")%>" required>

                            <br><br>
                            <label for="end-date">End date</label>
                            <input id="end-date" type="date" name="end-date" required>


                        </div>
                        <div class="item">
                            <label for="price">Insert Price</label>
                            <input form="create-expo" id="price" name="price" type="number" value="0" required>
                        </div>
                    </div>
                    <div class="right">
                        <div class="item">
                            <p>Event Description(UA)</p>
                            <input type="text" name="description-ua" required>
                        </div>
                        <div class="item">
                            <p>Event Description(EN)</p>
                            <input type="text" name="description-en" required>
                        </div>
                        <div class="item">
                            <p>Add Photo</p>
                            <input id="add-file" type="file" multiple
                                   style="border:none;">
                        </div>

                    </div>
                </div>
                <button class="btn create" form="create-expo" type="submit">CREATE</button>
            </form>
        </div>

    </section>


</section>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>