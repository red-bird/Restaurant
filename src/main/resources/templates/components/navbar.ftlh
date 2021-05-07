<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Главная</a>
                </li>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/food/admin">Админ меню</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users">Пользователи</a>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/food">Меню</a>
                    </li>
                </#if>
            </ul>

            <div class="navbar-text mr-3">${name}</div>
            <#if name != "Гость">
                <@l.logout />
            </#if>
        </div>
    </div>
</nav>