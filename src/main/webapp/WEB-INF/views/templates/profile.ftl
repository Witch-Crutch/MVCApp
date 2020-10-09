<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../../views/static/bootstrap.min.css">
    <link rel="stylesheet" href="../../../views/css/main.css">
    <link rel="stylesheet" href="../../../views/css/profile.css">
    <title>Личный кабинет</title>
    <script>
        var b = document.getElementById('overlay');

        function swa() {
            b.style.visibility = 'visible';
            b.style.opacity = '1';
            b.style.transition = 'all 0.7s ease-out 0s';
        }

        function swa2() {
            b.style.visibility = 'hidden';
            b.style.opacity = '0';
        }
    </script>
</head>
<body>
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
                <a class="nav-link " href="profile.html">Профиль</a>
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
        <img src="../../../views/assets/smoke_2.png">
    </div>
    <div class="container div_front">
        <div class="row">
            <div class="profile-cont_title">
                Личный кабинет
            </div>
            <div class="offset-3 col-md-3 right profile-cont_title">
                <!--TODO: исправить на абсолютный-->
                <button class="btn_mini" onclick="location.href='/quit'">Выйти</button>
            </div>
        </div>
        <div class="info">
            <div class="container">
                <div class="row">
                    <div class="profile-cont_subtitle left">
                        Информация
                    </div>
                    <div class="col-md-3 profile-cont_subtitle">
                        <button class="btn_mini_2" onclick="swa()" type="button">Изменить</button>
                    </div>
                </div>
                <div class="row info-cont">
                    <div class="col-md-2">
                        <a>
                            <img src="../../../views/assets/profile.png">
                        </a>
                    </div>

                    <div class="offset-2 col-md-2">
                        <div class="row info-cont_text_top">
                            Иванова
                        </div>
                        <div class="row info-cont_text">
                            Елизавета
                        </div>
                        <div class="row info-cont_text">
                            11.06.1996
                        </div>
                    </div>
                    <div class="offset-1 col-md-5">
                        <div class="row info-cont_text_top">
                            elizavetka96@mail.ru
                        </div>
                        <div class="row info-cont_text">
                            Пользуюсь услугами
                            бабушки Костыль уже три года.
                            Ни разу не подвела!
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Модальное окно -->
        <div id="overlay">
            <div class="popup">
                <button class="close" title="Закрыть окно" onclick="swa2()"></button>
                <p class="zag">Добавить фотографию</p>
                <p><span class="btn float-left">
                         Прикрепить файл <input type="file">
                        </span></p>
            </div>
        </div>

        <div class="div_back_right">
            <img src="../../../views/assets/smoke_2.png">
        </div>

        <div class="history">
            <div class="container">
                <div class="row">
                    <div class="profile-cont_subtitle">
                        История
                    </div>
                </div>
                <div class="container offset-1">
                    <div class="row history-cont_cards">
                        <div class="card" style="border-radius: 20px">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="card-body_img" style="padding: 70px 50px">
                                            <img src="../../../views/assets/services/2.png">
                                        </div>
                                    </div>
                                    <div class="container offset-1 col-md-6">
                                        <div class="row">
                                            <div class="card-body_title">
                                                Обряд на успех и удачу
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="card-body_text">
                                                Если вам, несмотря на кучу положительных качеств, <br>
                                                не удается построить карьеру и обрести работу мечты, <br>
                                                за которую бы платили хорошие деньги, проведите
                                                данный обряд и всё наладится!
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="card-body_price">
                                                Стоимость: 1290P
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row history-cont_cards">
                        <div class="card" style="border-radius: 20px">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="card-body_img" style="padding: 70px 50px">
                                            <img src="../../../views/assets/services/2.png">
                                        </div>
                                    </div>
                                    <div class="container offset-1 col-md-6">
                                        <div class="row">
                                            <div class="card-body_title">
                                                Обряд на успех и удачу
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="card-body_text">
                                                Если вам, несмотря на кучу положительных качеств,
                                                <br>не удается построить карьеру и обрести работу мечты,
                                                <br> за которую бы платили хорошие деньги, проведите
                                                данный обряд и всё наладится!
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="card-body_price">
                                                Стоимость: 1290P
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="div_back_left">
                    <img src="../../../views/assets/smoke_2.png">
                </div>
                <div class="row history-cont_btn">
                    <button class="offset-2 col-md-8 btn" type="submit" onclick="window.location='chat.html'">Написать
                        бабушке
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="openModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Название</h3>
                <a href="#close" title="Close" class="close">×</a>
            </div>
            <div class="modal-body">
                <p>Содержимое модального окна...</p>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <div class="row" style="padding-top: 1800px">
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