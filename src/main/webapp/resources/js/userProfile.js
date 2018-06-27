var ajaxUrl = "ajax/admin/users/";
var datatableApi;

$(function () {
    var id = $('#id').val();
    var pathname = window.location.pathname;
    if (pathname === "/profile") {
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        $('#id').val(data.id);
        $('#name').val(data.name);
        $('#email').val(data.email);
        $('#role').val(data.role.role);
        $('#password').val(data.password);
    });
    makeEditable();
}else
    makeEditable();
});



