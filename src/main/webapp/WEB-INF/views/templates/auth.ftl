<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main css=["register.css"] title="Авторизация">
<div class="container">
    <div class="div_back_left">
        <img src="../../../views/assets/smoke_2.png">
    </div>
    <div class="div_back_right">
        <img src="../../../views/assets/smoke.png">
    </div>

    <div class="container div_front">
        <div class="register-cont_title">
            Авторизация
        </div>

        <form class="form-signin" method="post" action="/auth">
            <div class="row cards">
                <div class="card offset-3 col-md-6" style="border-radius: 20px">
                    <div class="card-body text-center">
                        <div class="register-cont_input_top">
                            <!-- исправить на email -->
                            <input type="email" id="inputEmail" class="form-control register-cont_input"
                                   placeholder="Почта" required name="email">
                        </div>
                        <div class="register-cont_input_middle">
                            <input type="password" id="inputPassword" class="form-control register-cont_input"
                                   placeholder="Пароль" required name="password">
                        </div>

                        <div class="auth-checkbox">
                            <div class="checkbox mb-3">
                                <label class="checkbox_text">
                                    <input type="checkbox" value="remember-me" name="remember"> запомнить меня
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row cards">
                <button class="offset-4 col-md-4 btn" type="submit">Войти</button>
            </div>
        </form>
    </div>
</div>
</@base.main>