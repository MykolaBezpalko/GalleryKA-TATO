<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
        <link rel="stylesheet" href="./pages/styles/style.css" type="text/css">
    </head>
</head>
<body>
<%session.setAttribute("email","not-login");%>
<section class="create-body">
    <div class="picture">
    </div>
    <div class="fields">
        <div class="close"><a href="http://localhost:8080/gallery">
            <img src="pages/styles/images/close.png" alt="" style="  width: 30px;
            height: 30px">
        </a></div>
        <p class="create-account">WELCOME BACK!</p>
        <p class="allready-have">Log in to continue. have no account?</p>
        <p class="to-login"><a href="http://localhost:8080/gallery/create-account">Register</a></p>

        <form id="create-account" action="check-data" method="POST">
            <div class="field"> <p>E-MAIL</p>
                <input type="email" name="email" placeholder="Your e-mail" form="create-account"  pattern="[^ @]*@[^ @]*" required></div>

            <div class="field"><p>PASSWORD</p>
                <input type="password" name="password" placeholder="Your password" form="create-account"  pattern="[A-Za-z0-9]{4,16}" title="min: 4 max: 16 (Lattin and numbers)"  required></div>

            <div class="field" style="width: 50%;">
                <button class="submit-creation" type="submit" form="create-account">
                    LOG IN
                </button>
                <button class="submit-creation" form="logadmin">
                    LOG IN AS ADMINISTRATOR
                </button>
            </div>
        </form>
        <form id="logadmin" action="pages/admin-pages/login-as-admin.jsp"></form>

    </div>
</section>

</body>
</html>