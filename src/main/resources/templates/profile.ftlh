<#import "components/common.ftlh" as c>

<@c.page>
    <h5>Редактирование ${username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Пароль : </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Почта : </label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="${email!''}" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><button type="submit" class="btn btn-primary my-2">
                Сохранить
            </button></div>
    </form>
</@c.page>
