<#import "components/common.ftlh" as c>

<@c.page>
    <h4>Корзина:</h4>
    <hr>
    <div class="row row-cols-3 row-cols-md-3 g-4">
        <#list goodList as good>
            <div class="col">
                <div class="card my-3">
                    <#if good.food.filename??>
                        <img src="/img/${good.food.filename}" class="card-img-top" alt="${good.food.name}">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">Название: ${good.food.name}</h5>
                        <p class="card-text">Кол-во: ${good.quantity}</p>
                    </div>
                </div>
            </div>
        <#else>
            <div>В корзине пусто.</div>
        </#list>
    </div>
</@c.page>