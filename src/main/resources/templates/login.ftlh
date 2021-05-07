<#import "components/common.ftlh" as c>
<#import "components/login.ftlh" as l>

<@c.page>
    Авторизация
    <@l.login "/login" />
    <a href="/registration">Регистрация</a>
</@c.page>
