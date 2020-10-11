<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../../views/static/bootstrap.min.css">
    <link rel="stylesheet" href="../../../views/css/main.css">
    <link rel="stylesheet" href="../../../views/css/register.css">
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
            <div class="col-md-3 phone_top">
                <div class="row">
                    <a href="tel:+79186663333" class="ml-auto phone"> +7 (918) 666-33-33</a>
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

        <!-- TODO: заменить на абсолютный -->
        <form class="form-signin" method="post" action="/register">
            <div class="row cards">
                <div class="card border-white offset-3 col-md-6 card_settings">
                    <div class="card-body text-center ">
                        <div class="register-cont_input_top">
                            <input type="text" id="last_name" class="form-control register-cont_input"
                                   placeholder="Фамилия" required name="lastname">
                        </div>

                        <div class="register-cont_input_middle">
                            <input type="text" id="first_name" class="form-control register-cont_input"
                                   placeholder="Имя" required name="name">
                        </div>

                        <div class="register-cont_input_middle">
                            <input type="email" id="inputEmail" class="form-control register-cont_input"
                                   placeholder="Почта" required name="email">
                        </div>

                        <div class="register-cont_input_middle">
                            <input type="password" id="inputPassword" class="form-control register-cont_input"
                                   placeholder="Пароль" required name="password">
                        </div>

                        <div class="register-cont_input_bottom">
                            <input type="password" id="repeatPassword" class="form-control register-cont_input"
                                   placeholder="Повторите пароль" required name="password_again">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row register-cont_input_top">
                <button class=" offset-4 col-md-4 btn" type="submit">Регистрация</button>
            </div>
        </form>

    </div>
</div>


<!--footer-->
<footer>
    <div class="container">
        <div class="row" style="padding-top: 900px">
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
                    <a href="tel:+79186663333" class="ml-auto phone"> +7 (918) 666-33-33</a>
                </div>
                <div class="row">
                    <a class="nav-link  ml-auto" href="contact.html">Заказать
                        звонок</a>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>