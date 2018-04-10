var ajaxUrl = "ajax/admin/restaurants/";
var datatableApi;
var id;

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
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        $('#id').val(data.id);
        $('#name').val(data.name);
        $('#editRow').modal();
    });
}
function addMeals() {
    $("#detailsFormMeals").find(":input").val("");
    $('#listMeals').modal("hide");
    $("#editMeals").modal();
}

function editMeals() {
    id=$('#rest_id').val();
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        $('#restaurant_id').val(data.id);
        $('#meal_id').val(data.meals[0].id);
        $('#meal_name').val(data.meals[0].meal);
        $('#meal_price').val(data.meals[0].price);
        $('#listMeals').modal("hide");
        $('#editMeals').modal();
    });
}

function listMeals(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        var trHTML = '';
        clearTable("#listFormMeals");
        $('#rest_id').val(data.id);
        $.each(data.meals, function (i, item) {
            trHTML += '<tr><td>' + data.meals[i].meal + '</td>' +
                '<td>' + data.meals[i].price + '</td>' +
                '<td>' + '<a onclick=editMeals()><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>' + '</td>' +
                '<td>' + '<a onclick=deleteMeal('+data.meals[i].id+')><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>' + '</td>' +
                '</tr>';
        });
        $('#listFormMeals').append(trHTML);
        $('#listMeals').modal();
    });
}


function clearTable(string) {
    $(string).find("td").empty();
    $("td:empty").remove();
    $("tr:empty").remove();
}

function saveMeals() {
    form = $("#detailsFormMeals");
    id=$('#rest_id').val();
    $.ajax({
        type: "POST",
        url: ajaxUrl + id + "/meals",
        data : {
            id : $('#meal_id').val(),
            meal : $('#meal_name').val(),
            price : $('#meal_price').val()
        },
        success: function () {
            $("#editMeals").modal("hide");
            successNoty("Saved");
            listMeals(id);
        }
    });
}

function saveRestaurant() {
    form = $("#detailsFormRestaurant");
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data : {
            id : $('#id').val(),
            name : $('#name').val()
        },
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}

function deleteMeal(id) {
   // rest=$('#rest_id').val();
    $.ajax({
        type: "DELETE",
        url: ajaxUrl +"meals/"+ id,
        success: function () {
            successNoty("Deleted");
            listMeals($('#rest_id').val());
        }
    });
}