<#ftl encoding="UTF-8">
<#import "layouts/base.ftl" as base>
<@base.main title="Заказать звонок" css=["contact.css"]>
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
                        <form class="form-contact" style="padding-left: 120px; padding-right: 120px" method="post" action="/contact">
                            <div style="padding-top: 50px">
                                <input type="tel" id="inputPhone" class="form-control cont_input" name="tel">
                            </div>

                            <div class="offset-3 cont_btn">
                                <button class="btn" type="submit">
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