<#macro login path isRegisterForm>
    <#if isRegisterForm>
        <div>Регистрация</div>
    <#else>
        <div>Авторизация</div>
    </#if>
    <form action="${path}" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Имя пользователя: </label>
            <div class="col-sm-6">
                <input class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       value="<#if user??>${user.username}</#if>"
                       type="text" name="username"
                       placeholder="Имя пользователя" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-6">
                <input class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       type="password"
                       name="password"
                       placeholder="Пароль" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Повторный пароль: </label>
                <div class="col-sm-6">
                    <input class="form-control ${(passwordConfirmError??)?string('is-invalid', '')}"
                           type="password"
                           name="passwordConfirm"
                           placeholder="Введите пароль еще раз" />
                    <#if passwordConfirmError??>
                        <div class="invalid-feedback">
                            ${passwordConfirmError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label"> Почта: </label>
                <div class="col-sm-6">
                    <input class="form-control ${(emailError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.email}</#if>"
                           type="email"
                           name="email"
                           placeholder="Почта" />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <#if !isRegisterForm>
            <div class="form-group form-check my-2">
                <input type="checkbox" class="form-check-input" name="remember-me">
                <label class="form-check-label" for="rememberMe">Запомнить меня</label>
            </div>
        </#if>
        <#if isRegisterForm>
            <div class="col-sm-6">
                <div class="g-recaptcha" data-sitekey="${key}"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </#if>
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