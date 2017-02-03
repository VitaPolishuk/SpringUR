function selectRole(th) {
    $.ajax({
        type: "POST",
        url: "/selectRole",
        data: JSON.stringify({
            id_roles: th.value,
        }),
        contentType: 'application/json',
        mimeType: 'application/json',
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            $('#name_roles').val(data.name_roles);
        }
    })
}
function changeRole() {
    if (document.getElementById("name_roles").value != "") {
        $.ajax({
            type: "POST",
            url: "/changeRole",
            data: JSON.stringify({
                id_roles: document.getElementById("id_roles").value,
                name_roles: document.getElementById("name_roles").value,
            }),

            dataType: "json",
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                $('#name_roles').val("");
                templateSelect(data);

            }
        })
    }
}
function addRole() {
    if (document.getElementById("name_roles").value != "") {
        $.ajax({
            type: "POST",
            url: "/addRole",
            data: JSON.stringify({name_roles: document.getElementById("name_roles").value}),
            dataType: "json",
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                $('#name_roles').val("");
                templateSelect(data);
            }
        })
    }
}
function deleteRole() {
    if (document.getElementById("name_roles").value != "") {
        $.ajax({
            type: "POST",
            url: "/deleteRole",
            data: JSON.stringify({id_roles: document.getElementById("id_roles").value}),

            dataType: "json",
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                $('#name_roles').val("");
                templateSelect(data);
            }
        })
    }
}
function templateSelect(role) {
    var tmpl = document.getElementById('templateSelect').innerHTML.trim();
    tmpl = _.template(tmpl);
    document.getElementById('cont').innerHTML = tmpl({
        listRole: role,
    });

}