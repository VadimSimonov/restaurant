var ajaxUrl = "ajax/admin/restaurants/";
var datatableApi;
var id;

$(function () {
    datatableApi = $("#datatableRestaurants").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "meals",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<td><a onclick='listMeals(" + row.id + ")'><span class='glyphicon glyphicon-list' aria-hidden='true'></span></a></td>"
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
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
    $("#modalTitleRestaurant").html(i18n["editTitle"]);
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
    $("#modalTitleMeals").html(i18n["addTitleMeals"]);
    $("#detailsFormMeals").find(":input").val("");
    $('#listMeals').modal("hide");
    $("#editMeals").modal();
}

function editMeals(mealId) {
    $("#modalTitleMeals").html(i18n["editTitleMeals"]);
    id=$('#rest_id').val();
    $.ajax({
        type: "GET",
        url: ajaxUrl + id + "/meal" + mealId
    }).done(function (data) {
        //  $('#restaurant_id').val(data.id);
        $('#meal_id').val(data.id);
        $('#meal_name').val(data.meal);
        $('#meal_price').val(data.price);
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
                '<td>' + '<a onclick=editMeals(' + data.meals[i].id + ')><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>' + '</td>' +
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
            successNoty("common.saved");
            listMeals(id);
        }
    });
}

function saveRestaurant() {
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
            successNoty("common.saved");
        }
    });
}

function deleteMeal(id) {
    $.ajax({
        type: "DELETE",
        url: ajaxUrl +"meals/"+ id,
        success: function () {
            successNoty("common.deleted");
            listMeals($('#rest_id').val());
        }
    });
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='restaurantsEditRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}