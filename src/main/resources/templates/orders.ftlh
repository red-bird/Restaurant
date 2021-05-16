<#import "components/common.ftlh" as c>
<#assign sum = 0>
<#assign counter = 1>
<@c.page>
    <div>
        <h2 class="text-center">Заказы:</h2>
    </div>
    <hr>
    <#list orderList as order>
        <h5 class="text-center">Заказ №${counter}<#assign counter +=1></h5>
        <ul class="list-group mb-3">
            <li class="list-group-item">${order.date}</li>
            <li class="list-group-item">
                <#list order.goods as good>
                    <ul class="list-group mb-3">
                        <li class="list-group-item">Название: ${good.food.name}</li>
                        <li class="list-group-item">Кол-во: ${good.quantity}</li>
                        <li class="list-group-item">Цена: ${good.food.price * good.quantity}</li>
                    </ul>
                </#list>
            </li>
            <li class="list-group-item">
                <#list order.goods as good>
                    <#assign sum += good.quantity * good.food.price>
                </#list>
                Итоговая сумма: ${sum}
                <#assign sum = 0>
            </li>
        </ul>
        <#else>
        <div>В заказах пусто</div>
    </#list>
</@c.page>