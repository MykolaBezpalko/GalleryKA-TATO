<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>KA-TATO GALLERY</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Libre+Franklin:wght@400;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/style.css">
    </head>
</head>
<body>
<section class="create-body">
    <div class="picture">
    </div>
    <div class="fields">
        <div class="close"><a href="http://localhost:8080/gallery">
            <img src="${pageContext.request.contextPath}/pages/styles/images/close.png" alt="" style="  width: 30px;
            height: 30px">
        </a></div>
        <p class="create-account">
            <fmt:message key="admin-login-page.greeting"/>
        </p>
        <p class="allready-have">
            <fmt:message key="admin-login-page.login-to-cont"/>
        </p>

        <form id="create-account" action="${pageContext.request.contextPath}/admin" method="POST">
            <div class="field"> <p><fmt:message key="login-page.email"/></p>
                <input type="email" name="email" placeholder="Your e-mail" form="create-account"  pattern="[^ @]*@[^ @]*" required></div>

            <div class="field"><p><fmt:message key="login-page.password"/></p>
                <input type="password" name="password" placeholder="Your password" form="create-account"  pattern="[A-Za-z0-9]{4,16}" title="min: 4 max: 16 (Lattin and numbers)"  required></div>

            <div class="field" style="width: 50%;">
                <button class="submit-creation" type="submit" form="create-account">
                    <fmt:message key="login-page.login"/>
                </button>
            </div>
        </form>

    </div>
</section>

</body>
</html>