<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>KA-TATO GALLERY</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
              crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Libre+Franklin:wght@400;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="styles/style.css">
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
                            <a class="nav-link" aria-current="page" href="#">TICKETS & VISIT </a>
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
                <button class="language btn" form="language">
                    EN
                </button>
                <button class="language btn">
                    UA
                </button>
            </form>
        </div>
    </section>
    <div class="slogan">
        <h1>
            Design
            <br>
            Living
            <br>
            Tendency
        </h1>
    </div>

</section>
<section class="exhibitions">

    <div class="exposition">
        <p class="exhibition_place">The Great Hall</p>
        <div class="exhibition_photo"></div>
        <div class="exhibition_date"></div>
        <p class="exhibition_name">GRISHIGIANO. Simplicity is the highest form of sophistication</p>
        <button class="exhibition_buy">500 UAH</button>
    </div>

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

</body>
</html>