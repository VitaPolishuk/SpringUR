<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ddd.controllers.User" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователи</title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.3.3/underscore-min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="/pages/usersJs.js"></script>
    <link rel="stylesheet" href="/pages/style.css" type="text/css"/>
    <style>

    </style>
</head>
<body onload = "templateTable(listUse, listRol); templateSelect(listRol,0)">
<script>
    listUse = ${listUser};
    listRol = ${listRole};
</script>
<form name="users" method="post" id="formx">
    <input type='hidden' name="nevidimka" id="nevidimka">
    <h3><a href="http://localhost:8080/">На главную</a></h3>
    <script>
        _.templateSettings = {
            evaluate: /{{([\s\S]+?)}}/g,
            interpolate: /{{=([\s\S]+?)}}/g,
            escape: /{{-([\s\S]+?)}}/g
        };
    </script>
    <div class="mainblok">
        <div class="table" id="container">
        </div>

        <div class="inputData">

            <div class="inpTable">
                <table id="table" class="tableInput">
                    <tr>
                        <td class="td1"> Имя пользователя</td>
                        <td class="td2">
                            <input name="nameUser" type="text" id="nameU">
                        </td>
                    </tr>
                    <tr>
                        <td class="td3">Email</td>
                        <td class="td4">
                            <input name="email" type="text" id="email">
                        </td>
                    </tr>
                    <tr>
                        <td class="td5">Имя роли</td>
                        <td class="td6">
                            <div class="table" id="cont"></div>
                        </td>
                    </tr>
                </table>

            </div>
            <div class="Actions">

                <div class="Add"><input type="button" name="Add" value="Добавить" onclick="addUserTable(listRol)"></div>

                <div class="Save"><input type="button" name="Save" value="Сохранить" onclick="saveChange(listRol)"></div>
            </div>
        </div>
    </div>

</form>
<script type="text/template" id="templateSelect">
    <select name="id_roles" id="id_roles">
        <option value="roles" selected>Выберите роль</option>
        {{ for (var j = 0; j < listRole.length; j++) { }}
        {{ if (index!=0 & listRole[j].name_roles==index){ }}
        <option value="{{=listRole[j].id_roles}}" selected>{{=listRole[j].name_roles}}</option>
        {{ }else {}}
        <option value="{{=listRole[j].id_roles}}" >{{=listRole[j].name_roles}}</option>
        {{ } }}
        {{ } }}
    </select>
</script>


<script type="text/template" id="templateTable">
    <table id="tableAllUsers" class="tableUsers">
        <tr class="tr1 stroka">
            <th class="tr1">Имя пользователя</th>
            <th class="tr1">Email</th>
            <th class="tr1">Роль</th>
            <th class="tr1">Изменить/Удалить</th>
        </tr>
        {{ for (var i = 0; i < listUser.length; i++) { }}
        <tr class="tr1 stroka">
            <td class="tr1">{{= listUser[i].name_user }}</td>
            <td class="tr1">{{= listUser[i].email }}</td>
            {{ for (var j = 0; j < listRole.length; j++) { }}
            {{ if (listUser[i].id_roles == listRole[j].id_roles) { }}
            <td class="tr1" abbr="{{= listUser[i].id_roles }}">{{= listRole[j].name_roles}}</td>
            {{ } }}
            {{ } }}
            <td class="tr1"><input type="button" id="changeButton" name="{{= listUser[i].id_user }}" value="[/]"
                                   onclick="changeUser(this, listRol)">
                <input type="button" id="deleteButton" name="{{= listUser[i].id_user}}" value="[x]"
                       onclick="deleteUser(this,listRol)"></td>
        </tr>
        {{ } }}
    </table>
</script>

</body>
</html>