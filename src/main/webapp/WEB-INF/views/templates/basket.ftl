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
                    <#if basket??>
                        <#list basket.getProducts() as product>
                            <div class="row history-cont_cards">
                                <div class="card" style="border-radius: 20px">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="card-body_img" style="padding: 70px 50px">
                                                    <img src="../../../views/assets/services/${product.getImage()}">
                                                </div>
                                            </div>
                                            <div class="container offset-1 col-md-6">
                                                <div class="row">
                                                    <div class="card-body_title">
                                                        ${product.getName()}
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="card-body_text">
                                                        ${product.getDescription()}
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="card-body_price">
                                                        Стоимость: ${product.getPrice()}P
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <a class="btn_basket" href="/basketService?delete=${product.getId()}">Удалить</a>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>

            <div class="pay center">
                <a class="btn" href="/purchase">Оплатить</a>
            </div>
        </div>
    </div>
</@base.main>