var ajaxUrl = "ajax/admin/users/";
var datatableApi;

$(function () {
    var referrer = document.referrer;
    if (referrer.indexOf("register") > -1) {
        successNoty("common.saved");
    }
});



