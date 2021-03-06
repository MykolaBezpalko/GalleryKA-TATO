<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/mytag/mytag.tld" %>

<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>KA-TATO GALLERY</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
              crossorigin="anonymous">

        <link rel="stylesheet" href="pages/styles/style.css" type="text/css">
    </head>
</head>
<body>

<section class="main-page">
    <%Cookie lang = new Cookie("lang", "en");%>
    <c:if test="${param['locale'] != null}">
        <fmt:setLocale value="${cookie['lang']}" scope="session"/>
    </c:if>

    <fmt:setLocale value="${cookie['lang'].value}"/>
    <fmt:setBundle basename="lang"/>
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
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 text-center">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="#">
                                <fmt:message key="main-page.tickets-visit"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <fmt:message key="main-page.art-stories"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <fmt:message key="main-page.about"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <fmt:message key="main-page.webstore"/>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>

        <div class="mainButtons" style="margin-top: 20px">
            <form id="login" action="login-page">
                <button class="login btn" form="login">
                    <%--                    <c:set var="user" value="${}"--%>
                    <%--                    <c:if test="${user != null}"--%>
                    <fmt:message key="login-page.login"/>
                </button>
            </form>
            <div id="language" style="    align-items: center;display: flex;">
                <button class="language btn" type="submit" onclick="setCookie('en')">
                    EN
                </button>
                <button class="language btn" type="submit" onclick="setCookie('uk')">
                    UA
                </button>
            </div>
        </div>
    </section>
    <div class="slogan">
        <h1><fmt:message key="main-page.slogan"/></h1>
    </div>
</section>
<div class="sorting" style="display: flex; justify-content: space-around;">

    <div>
        <form action="datesorting" id="time">
            <input hidden name="number" value="1" form="time">
            <fmt:message key="main-page.date-sort"/>
            <button class="sortBtn" form="time" type="submit" name="sortType" value="dateFromBegin">????</button>
            <button class="sortBtn" form="time" type="submit" name="sortType" value="dateFromEnd">????</button>
        </form>
    </div>
    <div>
        <form action="pricesorting" id="price-sort">
            <input hidden name="number" value="1" form="price-sort">
            <fmt:message key="main-page.price-sort"/>
            <button class="sortBtn" form="price-sort" type="submit" name="sortType" value="minPrice">????</button>
            <button class="sortBtn" form="price-sort" type="submit" name="sortType" value="maxPrice">????</button>
        </form>
    </div>
    <div>
        <form action="namesorting" id="theme-sort">
            <input hidden name="number" value="1" form="theme-sort">
            <fmt:message key="main-page.name-sort"/>
            <button class="sortBtn" form="theme-sort" type="submit" name="sortType" value="themeBegin">????</button>
            <button class="sortBtn" form="theme-sort" type="submit" name="sortType" value="themeEnd">????</button>
        </form>
    </div>
</div>
<section class="exhibitions">
    <c:forEach items="${expos}" var="expo">
        <tag:card
                hallName="${expo.getHallsString()}" expoTheme="${expo.getTheme()}"
                beginDate="${expo.getBegin()}" endDate="${expo.getEnd()}"
                price="${expo.getPrice()}">
        </tag:card>
    </c:forEach>
</section>
<div>
    <tag:pagination sorting="${sorting}">
    </tag:pagination>
</div>

<%--change visual --%>
<section class="actionCall">
    <p>
        <fmt:message key="main-page.objects-expos"/>
    </p>
    <button class="btn actionCallBtn" type="button"
            onclick="window.location.href='http://localhost:8080/gallery/user-cabinet'">
        <fmt:message key="main-page.buy-ticket"/>
    </button>
</section>


<footer>
    <div class="logo">
        <a href="#">KA-TATO<br>GALLERY</a>
    </div>
    <div class="contact">800 500 30 20</div>
    <div class="contact">Lviv, Dreem street 13/A</div>
    <div class="contact">8.00 - 22.00</div>
    <div class="links">
        <div class="insta"><img src="pages/styles/images/INSTAGRAM.svg" alt=""></div>
        <div class="facebook"><img src="pages/styles/images/FACEBOOK.svg" alt=""></div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
        integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous">
</script>

<script>function setCookie(lang) {
    document.cookie = "lang=" + lang;
    window.location.reload();
}</script>
</body>
</html>