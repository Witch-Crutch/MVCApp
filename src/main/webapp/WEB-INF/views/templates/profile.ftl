<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main css=["profile.css"] title="Личный кабинет">
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

                            <!--ModalForm-->
                            <div class="text-center">
                                <a href="" class="btn_mini_2 btn-default" data-toggle="modal"
                                   data-target="#modalForm">Изменить</a>
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
                            <button class="offset-2 col-md-8 btn" type="submit" onclick="window.location='chat.html'">
                                Написать
                                бабушке
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</@base.main>