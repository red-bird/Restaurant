<#macro menu>
    <hr>
    <#list foodList as food>
        <div><b>${food.name}</b></div>
        <div><span>${food.type}</span></div>
        <div><i>${food.description}</i></div>
        <div><strong>${food.price}</strong></div>
        <hr>
        <#else>
        <div>В меню ни одного блюда.</div>
    </#list>
</#macro>

<#macro send>
    <form action="/food/admin" method="post">
        <div><label> Название блюда<input type="text" name="name"/> </label></div>
        <div><label> К какой кухне относится<input type="text" name="type"/> </label></div>
        <div><label> Описание<input type="text" name="description"/> </label></div>
        <div><label> Цена<input type="text" name="price"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Добавить"/></div>
    </form>
</#macro>