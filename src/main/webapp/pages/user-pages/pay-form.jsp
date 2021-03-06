<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://tomcat.apache.org/example-taglib" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KA-TATO Personal</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/gallery/pages/styles/cabinet-style.css">
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
    <section class="body-content pay-form">
        <div class="picture-pay-form"></div>
        </div>
        <div class="cabinet-workingplace insert-info">
            <h1><fmt:message key="pay-form.total-price"/><br>
                <tag:getPrice chosenExpos="${sessionScope.get('choisenExpo')}"></tag:getPrice>
            </h1>
            <form action="buy" id="enterCardData" method="post">
                <div class="field"><p><fmt:message key="pay-form.card-number"/> </p>
                    <input required type="number" minlength="16" maxlength="16" placeholder="Your Card Number"
                           pattern="[0-9]{16}">
                </div>
                <div class="field"><p><fmt:message key="pay-form.cardholder"/></p>
                    <input required type="text" placeholder="Cardholder">
                </div>
                <div class="field">
                    <input required type="number" placeholder="Month" maxlength="2" minlength="1">
                    <input required type="number" placeholder="Year" maxlength="4" minlength="4">
                </div>
                <div class="field"><p>CVV</p>
                    <input required type="number" maxlength="3" minlength="3">
                </div>
                <div class="field buttons">
                    <button class="btn" type="submit" form="enterCardData">
                        <fmt:message key="pay-form.pay"/>
                    </button>

                    <button class="btn" type="button"
                            onclick="window.location.href='http://localhost:8080/gallery/user-cabinet'">
                        <fmt:message key="pay-form.cancel"/>
                    </button>
                </div>
            </form>
        </div>
    </section>
</section>
<footer>
    <div class="logo">
        <a href="http://localhost:8080/gallery/">KA-TATO<br>GALLERY</a>
    </div>
    <div class="contact">800 500 30 20</div>
    <div class="contact">Lviv, Dreem street 13/A</div>
    <div class="contact">8.00 - 22.00</div>
    <div class="links">
        <div class="insta"><img src="pages/styles/images/INSTAGRAM.svg" alt=""></div>
        <div class="facebook"><img src="pages/styles/images/FACEBOOK.svg" alt=""></div>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>function setCookie(lang) {
    document.cookie = "lang=" + lang;
    window.location.reload();
}</script>

</body>
</html>
