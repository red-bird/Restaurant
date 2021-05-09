<#import "components/common.ftlh" as c>
<#import "components/food.ftlh" as f>
<#include "components/security.ftlh">

<@c.page>
    ${message?ifExists}
    <#if isAdmin>
        <@f.send />
    </#if>
    <@f.menu />
</@c.page>