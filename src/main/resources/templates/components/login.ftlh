<#macro login path isRegisterForm>
    <#if isRegisterForm>
        <div>Регистрация</div>
    <#else>
        <div>Авторизация</div>
    </#if>
    <form action="${path}" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Имя пользователя : </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Имя пользователя" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Пароль : </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль" />
            </div>
        </div>
        <#if isRegisterForm>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Пароль : </label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="Почта" />
            </div>
        </div>
        </#if>
        <div class="form-group form-check my-2">
            <input type="checkbox" class="form-check-input">
            <label class="form-check-label" for="rememberMe">Запомнить меня</label>
        </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><button type="submit" class="btn btn-primary mb-3">
                <#if isRegisterForm>Зарегестрироваться<#else>Войти</#if>
            </button></div>
        <#if isRegisterForm>
            <a href="/login" class="btn btn-link" role="button">Уже зарегестрированы?</a>
        <#else>
            <a href="/registration" class="btn btn-link" role="button">Регистрация</a>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-outline-primary">Выйти</button>
    </form>
</#macro>