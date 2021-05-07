<#import "components/common.ftlh" as c>
<#import "components/login.ftlh" as l>

<@c.page>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>