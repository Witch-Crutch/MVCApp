<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main title="Корзина" css=["profile.css", "basket.css"]>
    <div class="container">
        <div class="div_back_left">
            <img src="../../../views/assets/smoke_2.png">
        </div>
        <div class="div_back_right">
            <img src="../../../views/assets/smoke.png">
        </div>

        <div class="container div_front">
            <div class="profile-cont_title center">
                Корзина
            </div>

            <div class="list-services">
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
                            <a class="btn_basket">Удалить</a>
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
                            <a class="btn_basket">Удалить</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="pay center">
                <a class="btn" href="#">Оплатить</a>
            </div>
        </div>
    </div>
</@base.main>