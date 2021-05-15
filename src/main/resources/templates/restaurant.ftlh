<#import "components/common.ftlh" as c>
<#import "components/restaurant.ftlh" as r>
<#include "components/security.ftlh">

<@c.page>
    ${message?ifExists}
    <#if isAdmin>
        <@r.send />
    </#if>
    <@r.list isAdmin/>
</@c.page>