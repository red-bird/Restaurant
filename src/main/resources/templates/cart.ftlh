<#import "components/common.ftlh" as c>

<@c.page>
    <div>
        <h2 class="text-center">Корзина:</h2>
    </div>
    <form action="/cart/order" method="post" class="mb-3">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Оформить</button>
    </form>
    <a href="/orders">
        <button class="btn btn-primary" type="submit">К заказам</button>
    </a>
    <hr>
    <#if quantityError??>
    <div class="alert alert-danger" role="alert">
        Количество блюд не может быть меньше одного!
    </div>
    </#if>
    <div class="row row-cols-3 row-cols-md-3 g-4">
        <#list goodList as good>
            <div class="col">
                <div class="card my-3">
                    <#if good.filename??>
                        <img src="/img/${good.filename}" class="card-img-top" alt="${good.name}">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title text-center">Название: ${good.name}</h5>
                        <form action="/cart" method="post">
                            <div class="form-group mb-1 ${(quantityError??)?string('is-invalid', '')}">
                                <input class="form-control mb-1"
                                       type="number"
                                       name="quantity"
                                       value="${good.quantity}">
                                <#if quantityError??>
                                    <div class="invalid-feedback">
                                        ${quantityError}
                                    </div>
                                </#if>
                                <input type="hidden" name="goodId" value="${good.id}" />
                                <input type="hidden" name="_csrf" value="${_csrf.token}" placeholder="Количество" />
                                <button class="btn btn-primary" type="submit">Ок</button>
                            </div>
                        </form>
                        <form method="post" action="/cart/delete">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="hidden" name="id" value="${good.id}">
                            <button class="btn btn-danger" type="submit">Удалить</button>
                        </form>
                    </div>
                </div>
            </div>
        <#else>
            <div>В корзине пусто.</div>
        </#list>
    </div>
</@c.page>