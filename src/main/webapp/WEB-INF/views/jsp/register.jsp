<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.10.2020
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file="../../../views/css/register.css"%>
    </style>
</head>
<body>
<!--navbar-->
<nav>
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <a href="main.html"><img src="../../../views/assets/main/logo.png" class="logo"></a>
            </div>
            <div class="offset-md-1 col-md-2 links">
                <a class="nav-link " href="advantages.html">Преимущества</a>
            </div>
            <div class="col-md-1 links">
                <a class="nav-link " href="stages.html">Этапы</a>
            </div>
            <div class="col-md-1 links">
                <a class="nav-link " href="services.html">Услуги</a>
            </div>
            <div class="col-md-2 links">
                <a class="nav-link " href="auth.html">Вход</a>
            </div>
            <div class="col-md-3" style="padding-top: 25px">
                <div class="row">
                    <h4 class="nav-link  ml-auto phone">
                        +7 (918) 666-33-33 </h4>
                </div>
                <div class="row">
                    <a class="nav-link  ml-auto" href="contact.html">Заказать
                        звонок</a>
                </div>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="div_back_left">
        <img src="../../../views/assets/smoke.png">
    </div>
    <div class="div_back_right">
        <img src="../../../views/assets/smoke.png">
    </div>
    <div class="container div_front">
        <div class="register-cont_title">
            Регистрация
        </div>

        <div class="row cards">
            <div class="card border-white offset-3 col-md-6" style="border-radius: 20px">
                <div class="card-body text-center ">
                    <form class="form-signin" method="post" action="/register">
                        <div class="register-cont_input_top">
                            <input type="text" id="inputEmail" class="form-control register-cont_input"
                                   placeholder="Login"
                                   required name="name">
                        </div>

                        <div class="register-cont_input_middle">
                            <input type="password" id="inputPassword" class="form-control register-cont_input"
                                   placeholder="Password"
                                   required name="password">
                        </div>

                        <div class="register-cont_input_bottom">
                            <input type="password" id="repeatPassword" class="form-control register-cont_input"
                                   placeholder="Password"
                                   required name="password_again">
                        </div>
                        <div class="row" style="padding-top: 50px">
                            <button class=" offset-4 col-md-4 btn" type="submit">Регистрация</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>


<!--footer-->
<footer>
    <div class="container">
        <div class="row" style="padding-top: 800px">
            <div class="offset-1 col-md-3">
                <a href="main.html"><img src="../../../views/assets/main/logo.png"></a>
            </div>
            <div class="offset-1 col-md-2">
                <div class="row"><a class="nav-link" href="advantages.html">Преимущества</a></div>
                <div class="row"><a class="nav-link" href="stages.html">Этапы</a></div>
                <div class="row"><a class="nav-link" href="services.html">Услуги</a></div>
                <div class="row"><a class="nav-link" href="auth.html">Вход</a></div>
            </div>
            <div class="offset-2 col-md-3">
                <div class="row">
                    <h4 class="nav-link ml-auto phone">
                        +7 (918) 666-33-33 </h4>
                </div>
                <div class="row">
                    <a class="nav-link ml-auto" href="contact.html">Заказать
                        звонок</a>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
