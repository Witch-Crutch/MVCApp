<#ftl encoding="UTF-8">
<#import "layouts/base.ftl" as base>
<@base.main css=["services.css"] title="Мои услуги">
    <div class="content">
        <div class="container">
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
                                           placeholder="Введите текст для поиска" name="input" value="">
                                </div>
                                <div class="offset-1 col-md-2">
                                    <button class="btn services_btn" type="submit">Искать</button>
                                </div>
                                <div class="row filter">
                                    <div class="offset-2 col-md-5 justify-content-center">
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="materialGroupExample1"
                                                   name="groupOfMaterialRadios">
                                            <label class="form-check-label" for="materialGroupExample1">
                                                Популярные </label>
                                        </div>
                                    </div>

                                    <div class="offset-2 col-md-5 justify-content-center">
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="materialGroupExample2"
                                                   name="groupOfMaterialRadios" checked>
                                            <label class="form-check-label" for="materialGroupExample2"> Цена </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

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
                                                <#--                                                <button class="btn services_btn"-->
                                                <#--                                                        onclick="location.href='/basketService?add=${product.getId()}'">-->
                                                <#--                                                TODO здесь изменения от фронта-->
                                                <button class="btn services_btn"
                                                        onclick="d(${product.getId()})">
                                                    Воспользоваться
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>


                <div class="row">
                    <div class=" offset-4 col-md-4 center serv-cont_title ">
                        <button class="btn btn_ajax" onclick="startAjax()">Показать ещё</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        function d(id) {
            alert('Услуга добавлена в корзину! Уже начинаю колдовать!');
            window.location.href='/basketService?add='+id;
        }
    </script>
</@base.main>