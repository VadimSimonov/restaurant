var form;

function makeEditable() {
    form =$('#detailsForm');

    $(".delete").click(function () {
        deleteRow($(this).attr("id"));
    });

    form.submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    $("#detailsForm").find(":input").val("");
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
            $('#password').val(data.password);
        $('#editRow').modal();
    });

    /*
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
             form.find("input[name='" + key + "']").val(frm(key, value));
          //  $("#modalTitle").find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
    */
}


function save() {
    form = $("#detailsForm");
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
            $("#editRow").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}



/*
function save() {
    var data = {}
    data["id"]=$("#id").val();
    data["name"]=$("#name").val();
    data["password"]=$("#password").val();
    data["email"]=$("#email").val();
    data["roles"]=$("#roles").val();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: JSON.stringify(data),
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}
*/



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