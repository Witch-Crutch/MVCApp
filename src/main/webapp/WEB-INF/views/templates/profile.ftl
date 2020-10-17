<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main css=["profile.css"] title="Личный кабинет">
    <div class="content">
        <div class="container">
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
                                    <img src="../../../views/assets/user/${user.getProfileImg()}">
                                </a>
                            </div>

                            <div class="offset-2 col-md-2">
                                <div class="row info-cont_text_top">
                                    ${user.getLastname()}
                                </div>
                                <div class="row info-cont_text">
                                    ${user.getName()}
                                </div>
                                <div class="row info-cont_text">
                                    11.06.1996
                                </div>
                            </div>
                            <div class="offset-1 col-md-5">
                                <div class="row info-cont_text_top">
                                    ${user.getEmail()}
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

                <div class="history">
                    <div class="container">
                        <div class="row">
                            <div class="profile-cont_subtitle">
                                История
                            </div>
                        </div>
                        <div class="container offset-1">
                            <#if purchase??>
                                <#list purchase as purch>
                                    <div class="row history-cont_cards">
                                        <div class="card" style="border-radius: 20px">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <div class="card-body_img" style="padding: 70px 50px">
                                                            <img src="../../../views/assets/services/${purch.getImage()}">
                                                        </div>
                                                    </div>
                                                    <div class="container offset-1 col-md-6">
                                                        <div class="row">
                                                            <div class="card-body_title">
                                                                ${purch.getName()}
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="card-body_text">
                                                                ${purch.getDescription()}
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="card-body_price">
                                                                Стоимость: ${purch.getPrice()}P
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                        </div>
                        <div class="div_back_left">
                            <img src="../../../views/assets/smoke_2.png">
                        </div>
                        <div class="row history-cont_btn">
                            <button class="offset-2 col-md-8 btn" type="submit" onclick="location.href='/chat'">
                                Написать
                                бабушке
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.main>