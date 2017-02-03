function saveChange(listRol) {
    var table = document.getElementById("tableAllUsers");
    $.ajax({
        type: "POST",
        url: "/saveChange",
        data: JSON.stringify({
            id_user: document.getElementById("nevidimka").value,
            name_user: document.getElementById("nameU").value,
            email: document.getElementById("email").value,
            id_roles: document.getElementById("id_roles").value
        }),
        dataType: "json",
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data, textStatus, jqXHR) {
            templateTable(data, listRol);
            $('#nameU').val("");
            $('#email').val("");
            templateSelect(listRol, 0);
        }
    })
}
function changeUser(th, listRole) {
    var table = document.getElementById("tableAllUsers");
    var index = th.parentNode.parentNode.rowIndex;
    document.getElementById("nevidimka").value = th.name;

    document.getElementById("nameU").value = table.rows[index].cells[0].innerHTML;
    document.getElementById("email").value = table.rows[index].cells[1].innerHTML;
    templateSelect(listRole, table.rows[index].cells[2].innerHTML.trim());
}

function deleteUser(th, role) {
    $.ajax({
        type: "POST",
        url: "/delUser",
        data: JSON.stringify({
            id_user: th.name
        }),
        dataType: "json",
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data, textStatus, jqXHR) {
            templateTable(data, role);
        }
    })

}

function addUserTable(role) {
    $.ajax({
        type: "POST",
        url: "/addUserTable",
        data: JSON.stringify({

            name_user: document.getElementById("nameU").value,
            email: document.getElementById("email").value,
            id_roles: document.getElementById("id_roles").value
        }),
        dataType: "json",
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data, textStatus, jqXHR) {
            $('#nameU').val("");
            $('#email').val("");
            templateTable(data, role);
        }
    })
}
function templateTable(user, role) {

    var tmpl = document.getElementById('templateTable').innerHTML.trim();
    tmpl = _.template(tmpl);
    document.getElementById('container').innerHTML = tmpl({
        listUser: user,
        listRole: role
    });

}
function templateSelect(role, ind) {
    var tmpl = document.getElementById('templateSelect').innerHTML.trim();
    tmpl = _.template(tmpl);
    document.getElementById('cont').innerHTML = tmpl({
        listRole: role,
        index: ind
    });

}