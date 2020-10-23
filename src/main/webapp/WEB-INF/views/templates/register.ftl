<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main title="Регистрация" css=["register.css"]>
    <div class="content">
        <div class="container">
            <div class="register-cont_title">
                Регистрация
            </div>
            <form class="form-signin" method="post" action="/register">
                <div class="row cards">
                    <div class="card border-white offset-3 col-md-6 card_settings">
                        <div class="card-body text-center ">
                            <div class="register-cont_input_top">
                                <label for="last_name"></label>
                                <input type="text" id="last_name" class="form-control register-cont_input"
                                       placeholder="Фамилия" required name="lastname" pattern="^[A-Za-zА-ЯЁа-яё]+$">
                            </div>

                            <div class="register-cont_input_middle">
                                <label for="first_name"></label>
                                <input type="text" id="first_name" class="form-control register-cont_input"
                                       placeholder="Имя" required name="name" pattern="^[A-Za-zА-ЯЁа-яё]+$">
                            </div>

                            <div class="register-cont_input_middle">
                                <label for="inputEmail"></label>
                                <input type="email" id="inputEmail" class="form-control register-cont_input"
                                       placeholder="Почта" required name="email">
                            </div>

                            <div class="register-cont_input_middle">
                                <label for="inputPassword"></label>
                                <input type="password" id="inputPassword" class="form-control register-cont_input"
                                       placeholder="Пароль" required name="password"
                                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$">
                            </div>

                            <div class="register-cont_input_bottom">
                                <label for="repeatPassword"></label>
                                <input type="password" id="repeatPassword" class="form-control register-cont_input"
                                       placeholder="Повторите пароль" required name="password_again"
                                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$">
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
</@base.main>