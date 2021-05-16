<#macro list isAdmin>
    <h4>Список ресторанов:</h4>
    <hr>
    <div class="row row-cols-3 row-cols-md-3 g-4">
        <#list restaurantList as restaurant>
            <div class="col">
                <div class="card my-3">
<#--                    <#if restaurant.filename??>-->
<#--                        <img src="/img/${restaurant.filename}" class="card-img-top" alt="${restaurant.name}">-->
<#--                    </#if>-->
                    <div class="card-body">
                        <h5 class="card-title">${restaurant.address}</h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Телефон: ${restaurant.phone}</li>
                        <li class="list-group-item">Рабочее время: ${restaurant.worktime}</li>
                        <li class="list-group-item">Описание: ${restaurant.description}</li>
                        <#if isAdmin>
                            <form method="post" action="/restaurants/delete">
                                <input type="hidden" name="id" value="${restaurant.id}" />
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button class="btn btn-danger" type="submit">Удалить</button>
                            </form>
                        </#if>
                    </ul>
                </div>
            </div>
        <#else>
            <div>Не добавлено еще ни одного ресторана.</div>
        </#list>
    </div>
</#macro>

<#macro send>
    <div class="mb-3">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#restaurantSend" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить ресторан
        </a>
        <div class="collapse.show" id="restaurantSend">
            <div class="form-group mt-3">
                <form action="/restaurants" method="post">
                    <div class="form-group mb-1">
                        <input class="form-control ${(addressError??)?string('is-invalid', '')}"
                               value="<#if restaurant??>${restaurant.address}</#if>"
                               type="text"
                               name="address"
                               placeholder="Адрес"/>
                        <#if addressError??>
                            <div class="invalid-feedback">
                                ${addressError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-1">
                        <input class="form-control ${(phoneError??)?string('is-invalid', '')}"
                               value="<#if restaurant??>${restaurant.phone}</#if>"
                               type="text"
                               name="phone"
                               placeholder="Номер телефона" />
                        <#if phoneError??>
                            <div class="invalid-feedback">
                                ${phoneError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-1">
                        <input class="form-control ${(worktimeError??)?string('is-invalid', '')}"
                               value="<#if restaurant??>${restaurant.worktime}</#if>"
                               type="text"
                               name="worktime"
                               placeholder="Рабочее время" />
                        <#if worktimeError??>
                            <div class="invalid-feedback">
                                ${worktimeError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-3">
                        <textarea class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                                  name="description"
                                  placeholder="Описание"
                                  rows="5"></textarea>
                        <#if descriptionError??>
                            <div class="invalid-feedback">
                                ${descriptionError}
                            </div>
                        </#if>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group"><button class="btn btn-primary" type="submit">Добавить</button></div>
                </form>
            </div>
        </div>
    </div>
</#macro>