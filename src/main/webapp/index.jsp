<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>


<%@page import="com.gallery.webjava.db.AdminDAO" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>
<!DOCTYPE html>
<html lang="en">

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
                            <a class="nav-link" aria-current="page" href="#"><fmt:message key="main-page.tickets-visit"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">ART & STORIES</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">ABOUT </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">WEBSTORE</a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
        <div class="mainButtons">
            <form id="login" action="login-page">
                <button class="login btn" form="login">
                    LOG IN
                </button>
            </form>

            <form id="language" action="#">
                <button class="language btn" form="language" onclick="<fmt:setLocale value="en_GB"/>">
                    EN
                </button>
                <button class="language btn" form="language" onclick="<fmt:setLocale value="uk_UA"/>">
                    UA
                </button>
            </form>
        </div>
    </section>
    <div class="slogan">
        <h1><fmt:message key="main-page.slogan"/></h1>
    </div>
    <%response.setLocale(new Locale("en_GB"));%>
</section>
<section class="exhibitions">

    <%session.setAttribute("halls", (new AdminDAO().getAllExpositions()));%>
    <%
        session.setAttribute("email", "not-login");
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        session.setAttribute("today", format.format(d));
        session.setAttribute("locale", request.getLocale());

//        System.out.println(session.getAttribute("locale"));
    %>
    <%=session.getAttribute("today")%>

    <input type="date" value="<%=session.getAttribute("today")%>">
    ${"<br>"}


<%--    // foreach example--%>
<%--    <%=request.getLocale()%>--%>
<%--    ${"<br>"}--%>
<%--    <c:forEach items="${halls}" var="hall">--%>

<%--        <c:out value="${hall}">--%>
<%--            <div>--%>
<%--                <p>${hall.theme}</p>--%>
<%--                <button>${hall.price}</button>--%>
<%--            </div>--%>
<%--        </c:out>--%>
<%--    </c:forEach>--%>


        <div class="exposition">
            <p class="exhibition_place">The Great Hall</p>
            <div class="exhibition_photo"></div>
            <div class="exhibition_date">8 Aug 2021</div>
            <p class="exhibition_name">GRISHIGIANO. Simplicity is the highest form of sophistication</p>
            <button class="exhibition_buy">500 UAH</button>
        </div>

        <div class="exposition">
            <p class="exhibition_place">The Great Hall</p>
            <div class="exhibition_photo"></div>
            <div class="exhibition_date">13 Aug 2021</div>
            <p class="exhibition_name">EGENHÄNDIGT. Ceramics sculptures and scandi style.</p>
            <button class="exhibition_buy">450 UAH</button>
        </div>


        <div class="exposition">
            <p class="exhibition_place">The Great Hall</p>
            <div class="exhibition_photo"></div>
            <div class="exhibition_date">25 Aug 2021</div>
            <p class="exhibition_name">National Institute of American Doll Artists. Art & dolls. </p>
            <form action="/getcard">
                <button class="exhibition_buy">420 UAH</button>
            </form>

        </div>

</section>

<section class="actionCall">
    <p>Objects & Exhibitions</p>
    <form id="actionCallBtn" action="#">
        <button class="buyBtn" form="actionCallBtn">BUY TICKET</button>
    </form>
</section>
<footer>
    <div class="logo">
        <a href="#">KA-TATO<br>GALLERY</a>
    </div>
    <div class="contact">800 500 30 20</div>
    <div class="contact">Lviv, Dreem street 13/A</div>
    <div class="contact">8.00 - 22.00</div>
    <div class="links">
        <div class="insta"><img src="styles/images/INSTAGRAM.svg" alt=""></div>
        <div class="facebook"><img src="styles/images/FACEBOOK.svg" alt=""></div>
    </div>
</footer>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
        integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>
</body>
</html>