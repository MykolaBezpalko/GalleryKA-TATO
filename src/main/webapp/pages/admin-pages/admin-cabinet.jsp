<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
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
        <div class="mainButtons" >
            <form id="login" action="logout">
                <button class="logout btn" form="login">
                    <fmt:message key="cabinet.logout"/>
                </button>
            </form>
            <div id="language" style="margin-top: 20px">
                <button class="language btn" type="submit" onclick="setCookie('en')">
                    EN
                </button>
                <button class="language btn" type="submit" onclick="setCookie('uk')">
                    UA
                </button>
            </div>
        </div>
    </section>
    <section class="body-content">
        <div class="cabinet-menu">
            <div class="profile-info">
                <div class="photo"></div>
                <p class="role">Administrator</p>
                <a href="#" class="profile-link">PROFILE</a>
                <a href="#" class="profile-link active">NEW EVENT</a>
                <a href="http://localhost:8080/gallery/admin/admin-cabinet/statistic" class="profile-link">STATISTICS</a>
                <a href="http://localhost:8080/gallery/admin/admin-cabinet/all-expo" class="profile-link">SEE ALL EVENTS</a>
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
                            <label for="price">Insert Price</label>
                            <input form="create-expo" id="price" name="price" type="number" value="0" required>
                        </div>

                        <div class="item">
                            <p>Event Description(UA)</p>
                            <input type="text" name="description-ua" required>
                        </div>

                        <div class="item">
                            <p>Event Description(EN)</p>
                            <input type="text" name="description-en" required>
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