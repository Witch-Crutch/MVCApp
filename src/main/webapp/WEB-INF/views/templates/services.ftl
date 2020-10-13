<#ftl encoding="UTF-8">
<#import "layouts/base.ftl" as base>
<@base.main css=["services.css"] title="Мои услуги">
    <div class="container">
        <div class="div_back_right">
            <img src="../../../views/assets/smoke.png">
        </div>

        <div class="container div_front">
            <div class="serv-cont_title">
                Мои услуги
            </div>

            <form class="search_form" method="get" action="/search">
                <div class="search_block">
                    <div class="container">
                        <div class="offset-2 col-md-9">
                            <div class="row">
                                <div class="col-md-7">
                                    <input type="search" id="inputSearch" class="form-control search_block_input"
                                           required name="input">
                                </div>
                                <div class="offset-1 col-md-2">
                                    <button class="btn services_btn" type="submit">Искать</button>
                                </div>
                                <div class="row filter">
                                    <div class="col-md-3">
                                        <button class="btn" type="submit">Популярные</button>
                                    </div>
                                    <div class="offset-2 col-md-3">
                                        <button class="btn" type="submit">Цена</button>
                                    </div>
                                    <div class="offset-1 col-md-3">
                                        <button class="btn" type="submit">Новинки</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="div_back_left">
                <img src="../../../views/assets/smoke_2.png">
            </div>

            <div class="services">
                <div class="container">
                    <div class="row">
                        <#if products??>
                            <#list products as product>
                                <div class="col-md-4">
                                    <div class="card services_card">
                                        <div class="card-body center">
                                            <div class="services_img">
                                                <img src="../../../views/assets/services/${product.getImage()}">
                                            </div>
                                            <div class="services_title">
                                                ${product.getName()}
                                            </div>
                                            <div class="services_price">
                                                ${product.getPrice()}Р
                                            </div>
                                            <div class="btn_pad">
                                                <button class="btn services_btn"
                                                        onclick="window.location='${product.getId()}.html'">
                                                    Воспользоваться
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>

                    <#--<div class="div_back_right">
                        <img src="../../../views/assets/smoke.png">
                    </div>-->

                    <#--<div class="div_back_left">
                        <img src="../../../views/assets/smoke_2.png">
                    </div>-->

                </div>


                <div class="row">
                    <div class=" offset-4 col-md-4 center serv-cont_title ">
                        <button class="btn btn_ajax" onclick="startAjax()">Показать ещё</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.main>