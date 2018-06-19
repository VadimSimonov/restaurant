var form;
var formM;
var formR;

function makeEditable() {
    form =$('#detailsForm');
    formR =$('#detailsFormRestaurant');
    formM =$('#detailsFormMeals');
    formRMenu =$('#listFormRestaurants');
    formP =$('#EditFormUser');

    $(".delete").click(function () {
        deleteRow($(this).attr("id"));
    });

    form.submit(function () {
        save();
        return false;
    });

    formM.submit(function () {
        saveMeals();
        return false;
    });

    formRMenu.submit(function () {
        addRestaurant();
        return false;
    });

    formR.submit(function () {
        saveRestaurant();
        return false;
    });

    formP.submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function add() {
    $("#detailsForm").find(":input").val("");
    $("#detailsFormRestaurant").find(":input").val("");
    $("#editRow").modal();
}


function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function editRow(id) {
   // $("#modalTitle").html(i18n["editTitle"]);
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
            $('#id').val(data.id);
            $('#name').val(data.name);
            $('#email').val(data.email);
            $('#role').val(data.role.role);
            $('#password').val(data.password);
        $('#editRow').modal();
    });
}


function save() {
    if (window.location.pathname==="/register"){
        ajaxUrl="/register"
    }
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        //data: form.serialize(),
        data : {
            id : $('#id').val(),
            name : $('#name').val(),
            email : $('#email').val(),
            role : $('#role').val(),
            password : $('#password').val()
        },
        success: function () {
            var pathname = window.location.pathname;
            successNoty("Saved");
            if (pathname==="/profile"){
                window.location.href='menu'
            }if (pathname==="/register"){
                window.location.href='login'
            }else
                $("#editRow").modal("hide");
                updateTable();
        }
    });
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='editRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}
    var failedNote;

    function closeNoty() {
        if (failedNote) {
            failedNote.close();
            failedNote = undefined;
        }
    }

    function successNoty(text) {
        closeNoty();
        new Noty({
            text: "<span class='glyphicon glyphicon-ok'></span> &nbsp;" + text,
            type: 'success',
            layout: "bottomRight",
            timeout: 1000
        }).show();
    }

    function failNoty(jqXHR) {
        closeNoty();
        failedNote = new Noty({
            text: "<span class='glyphicon glyphicon-exclamation-sign'></span> &nbsp;Error status: " + jqXHR.status,
            type: "error",
            layout: "bottomRight"
        }).show();
}