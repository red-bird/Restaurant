<#macro menu>
    <div>Меню:</div>
    <hr>
    <div class="row row-cols-3 row-cols-md-3 g-4">
        <#list foodList as food>
            <div class="col">
                <div class="card my-3">
                    <#if food.filename??>
                        <img src="/img/${food.filename}" class="card-img-top" alt="${food.name}">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${food.name}</h5>
                        <p class="card-text">${food.description}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Кухня: ${food.type}</li>
                        <li class="list-group-item">Цена: ${food.price}</li>
                    </ul>
                    <div class="card-body">
                        <a href="#" class="card-link">Card link</a>
                        <a href="#" class="card-link">Another link</a>
                    </div>
                </div>
            </div>
        <#else>
            <div>В меню ни одного блюда.</div>
        </#list>
    </div>
</#macro>

<#macro send>
    <div class="mb-3">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#foodSend" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить блюдо
        </a>
        <div class="collapse.show" id="foodSend">
            <div class="form-group mt-3">
                <form action="/food/admin" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-1">
                        <input class="form-control ${(nameError??)?string('is-invalid', '')}"
                               value="<#if food??>${food.name}</#if>" type="text" name="name" placeholder="Название"/>
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-1">
                        <input class="form-control ${(typeError??)?string('is-invalid', '')}"
                               value="<#if food??>${food.type}</#if>" type="text" name="type" placeholder="Кухня" />
                        <#if typeError??>
                            <div class="invalid-feedback">
                                ${typeError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-1">
                        <input class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                               value="<#if food??>${food.description}</#if>" type="text" name="description" placeholder="Описание" />
                        <#if dascriptionError??>
                            <div class="invalid-feedback">
                                ${descriptionError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-1">
                        <input class="form-control ${(priceError??)?string('is-invalid', '')}"
                               value="<#if food?? && food.price?? && (food.price > 0) >${food.price}<#else></#if>" type="text" name="price" placeholder="Цена" />
                        <#if priceError??>
                            <div class="invalid-feedback">
                                ${priceError}
                            </div>
                        </#if>
                    </div>
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Изображение блюда</label>
                        <input class="form-control" type="file" id="formFile" name="file" placeholder="Изображение">
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group"><button class="btn btn-primary" type="submit">Добавить</button></div>
                </form>
            </div>
        </div>
    </div>
</#macro>