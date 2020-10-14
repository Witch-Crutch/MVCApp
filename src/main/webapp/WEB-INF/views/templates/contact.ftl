<#ftl encoding="UTF-8">
<#import "layouts/base.ftl" as base>
<@base.main title="Заказать звонок" css=["contact.css"] scripts=["jquery.maskedinput.min.js", "contact.js"]>
    <div class="container">
        <div class="div_back_left">
            <img src="../../../views/assets/smoke.png">
        </div>
        <div class="div_back_right">
            <img src="../../../views/assets/smoke_2.png">
        </div>
        <div class="container div_front">
            <div class="offset-2 col-md-8 contact-card">
                <div class="card" style="border-radius: 20px">
                    <div class="card-body">
                        <div class="card-contact_title center">
                            Спасибо за желание <br> воспользоваться моими услугами! <br> Оставьте свой номер телефона <br>
                            для связи!
                        </div>
                        <form action="/contact" method="post">
                            <div class="form-group">
                                <input type="tel" class="form-control cont_input" id="phone" name="tel"
                                       placeholder="+7 (999) 99 99 999">
                            </div>
                            <div class="offset-4 cont_btn">
                                <button class="btn">
                                    Отправить
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.main>