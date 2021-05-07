<#import "components/common.ftlh" as c>
<#import "components/login.ftlh" as l>

<@c.page>
    Регистрация
    ${message?isExists}
    <@l.login "/registration" />
    <a href="/login">Уже есть аккаунт?</a>
</@c.page>