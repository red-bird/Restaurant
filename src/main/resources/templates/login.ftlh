<#import "components/common.ftlh" as c>
<#import "components/login.ftlh" as l>

<@c.page>
    ${message?ifExists}
    <@l.login "/login" false />
</@c.page>
