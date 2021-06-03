<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.gallery.webjava.db.entity.Hall" %>
<%@ page import="com.gallery.webjava.db.AdminDAO" %>
<%@ page import="java.util.*" %>
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

        <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" rel="stylesheet"/>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript">
            <%request.setAttribute("availableDates",session.getAttribute("availableDates"));%>
            var availableDates = [${availableDates}];
            $(function () {
                $('#start-date').datepicker({
                    beforeShowDay:
                        function (dt) {
                            return [available(dt), ""];
                        }
                    , changeMonth: true, changeYear: false,
                    dateFormat: 'yy-mm-dd'
                });
                $('#end-date').datepicker({
                    beforeShowDay:
                        function (dt) {
                            return [available(dt), ""];
                        }
                    , changeMonth: true, changeYear: false,
                    dateFormat: 'yy-mm-dd'
                });
            });

            function pad(num, size) {
                num = num.toString();
                while (num.length < size) num = "0" + num;
                return num;
            }

            function available(date) {

                dmy = date.getFullYear() + "-" + pad((date.getMonth() + 1), 2) + "-" + pad(date.getDate(), 2);
                if ($.inArray(dmy, availableDates) != -1) {
                    return false;
                } else {
                    return true;
                }
            }


        </script>


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
                <a class="navbar-brand" href="">
                    KA-TATO
                    <br>
                    GALLERY
                </a>

            </div>
        </nav>
        <div class="mainButtons">
            <form id="login" action="/gallery/logout">
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
                <a href="http://localhost:8080/gallery/admin/admin-cabinet/all-expo" class="profile-link">SEE ALL
                    EVENTS</a>
            </div>
        </div>
        <div class="cabinet-workingplace">
            <h1>Choose dates for new event</h1>
            <form action="${pageContext.request.contextPath}/admin/admin-cabinet/insert-info" id="create-expo"
                  method="POST">
                <div class="top">
                    <div class="left">
                        <div class="item">
                            <%
                                Date d = Calendar.getInstance().getTime();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                session.setAttribute("today", format.format(d));
                            %>
                            <label for="start-date">Begin date</label>
                            <input id="start-date" type="text" name="start-date" form="check-dates"
                                   value="<%=session.getAttribute("begin")%>" required>
                            <br><br><br>
                            <label for="end-date">End date</label>
                            <input id="end-date" type="text" name="end-date" form="check-dates"
                                   value="<%=session.getAttribute("end")%>" required>
                            <button id="check" class="btn create" form="check-dates" type="submit">
                                CHECK
                            </button>
                            <br>
                            <p> You choose:
                                <big><%=session.getAttribute("chosenHalls") + "<br>"%>
                                </big> and dates:
                                <%=session.getAttribute("begin")%> - <%=session.getAttribute("end")%>
                            </p>
                        </div>
                    </div>
                    <div class="right">
                        <div class="item">
                            <p class="title-item">Available halls for this period</p>
                            <%
                                List<Hall> halls = new AdminDAO().getAllHalls();
                                request.setAttribute("halls", halls);
                            %>
                            <ul>
                                <c:forEach items="${halls}" var="hall">
                                    <ol>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox"
                                                   name="hall" value="${hall.hallName}"
                                                   form="check-dates">
                                            <label class="form-check-label">
                                                <c:out value="${hall.hallName}"/>
                                            </label>
                                        </div>
                                    </ol>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <button class="btn create" form="create-expo" type="submit">NEXT</button>
            </form>
            <form id="check-dates" method="post" action="http://localhost:8080/gallery/admin/check-dates"></form>

        </div>
    </section>
</section>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous">
</script>

</body>
</html>