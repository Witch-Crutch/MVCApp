<footer>
    <div class="container" style="padding-top: 100px">
        <div class="row">
            <div class="offset-1 col-md-3">
                <img src="../../../../views/assets/main/logo.png">
            </div>
            <#--TODO: исправить на абсолютные-->
            <div class="offset-1 col-md-2">
                <div class="row"><a class="nav-link " href="advantages.html">Преимущества</a></div>
                <div class="row"><a class="nav-link " href="stages.html">Этапы</a></div>
                <div class="row"><a class="nav-link " href="services.html">Услуги</a></div>
                <#if user??>
                    <div class="row"><a class="nav-link " href="/profile">Профиль</a></div>
                    <div class="row"><a class="nav-link " href="basket.html">Корзина</a></div>
                <#else>
                    <div class="row"><a class="nav-link " href="/auth">Вход</a></div>
                    <div class="row"><a class="nav-link " href="/register">Регистрация</a></div>
                </#if>
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
