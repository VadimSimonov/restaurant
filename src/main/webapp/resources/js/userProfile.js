var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    var id=$('#id').val();
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
});



