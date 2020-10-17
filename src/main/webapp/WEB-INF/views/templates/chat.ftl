<#ftl encoding="UTF-8">
<#import "layouts/base.ftl" as base>
<@base.main title="Чат" css=["chat.css"]>
    <div class="content">
        <div class="container">
            <div class="offset-3 col-md-6 chat_padding">
                <div class="card chat-room" style="border-radius: 20px">
                    <div class="card-body">
                        <div class="chat-message">
                            <ul class="list-unstyled chat-1 scrollbar-light-blue">
                                <#if messages??>
                                    <#list messages as message>
                                        <li class="d-flex justify-content-between mb-4">
                                            <img src="../../../views/assets/user/${message.getSender().getProfileImg()}"
                                                 alt="avatar"
                                                 class="avatar rounded-circle ">
                                            <div class="chat-body white">
                                                <div class="header">
                                                    <strong class="primary-font">${message.getSender().getLastname()} ${message.getSender().getName()}</strong>
                                                    <small class="pull-right text-muted"><i
                                                                class="far fa-clock"></i> ${message.getDate()}</small>
                                                </div>
                                                <hr class="w-100">
                                                <#--                                                TODO исправить путь к картинке на шаблон-->
                                                <a href=" " style="display: block"><img
                                                            src=" "
                                                            class="w-25"></a>
                                                <p class="mb-0">
                                                    ${message.getMessage()}
                                                </p>
                                            </div>
                                        </li>
                                    </#list>
                                </#if>
                            </ul>

                            <form class="form-dispatch" action="/chat" method="post" enctype="multipart/form-data">
                                <div class="white">
                                    <div class="form-group basic-textarea">
                                    <textarea class="form-control" id="exampleFormControlTextarea2" rows="3"
                                              placeholder="Напишите сообщение ведьме здесь..." name="text"></textarea>
                                    </div>
                                </div>
                                <span class="btn float-left">Прикрепить файл <input type="file" name="file"
                                                                                    accept="image/*"></span>
                                <div>
                                    <button type="submit" class="btn float-right">Отправить</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.main>