var ajaxUrl = "ajax/admin/restaurants/";
var datatableApi;

$(function () {
    datatableApi = $("#datatableRestaurants").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "meals"
            },
            {
                "defaultContent": "Add",
                "orderable": false
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function restaurantsEditRow(id) {
    // $("#modalTitle").html(i18n["editTitle"]);
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        $('#id').val(data.id);
        $('#name').val(data.name);
        $('#editRow').modal();
    });
}

function saveRestaurant() {
    form = $("#detailsFormRestaurant");
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data : {
            id : $('#id').val(),
            name : $('#name').val(),
        //    meals :$('#meals').val(),
        },
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}