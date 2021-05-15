<#import "components/common.ftlh" as c>

<@c.page>
<#--    ${message?ifExists}-->
    <h4>Корзина:</h4>
    <hr>
    <ul class="list-group">
        <#list goodList as good>
        <li class="list-group-item">Блюдо: ${good.food.name} Цена: ${good.food.name} Кол-во: ${good.quantity}</li>
        <#else>
            <div>В корзине пусто.</div>
        </#list>
    </ul>
</@c.page>