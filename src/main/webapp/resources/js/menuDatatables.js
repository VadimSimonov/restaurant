var ajaxUrl = "ajax/admin/menu/";
var ajaxUrlRestaurants = "ajax/admin/restaurants";
var datatableApi;
var id;

$(function () {
    datatableApi = $("#datatableMenu").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "date"
            },
            {
                "data": "name"
            },
            {
                "data": "meals"
            },
            {
                "data": "vote"
            },
            {
                "defaultContent": "Plus",
                "orderable": false
            },
            {
                "defaultContent": "Minus",
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

function listMeals(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        var trHTML = '';
        clearTable("#listFormMeals");
        $.each(data, function (i, item) {
            trHTML += '<tr><td>' + data[i].meal + '</td>' +
                '<td>' + data[i].price + '</td>' +
                '</tr>';
        });
        $('#listFormMeals').append(trHTML);
        $('#listMeals').modal();
    });
}

function voitePlus(id,pm) {
    $.ajax({
        type: "POST",
        url: ajaxUrl + id,
        data : {
            userId : $('#user_id').val(),
            pm : pm
        },
        success: function () {
            updateTable();
            successNoty("Voted");
        }
    });
}

function voiteMinus(id,pm) {
    $.ajax({
        type: "POST",
        url: ajaxUrl + id,
        data : {
            userId : $('#user_id').val(),
            pm : pm
        },
        success: function () {
            updateTable();
            successNoty("Voted");
        }
    });
}

function clearTable(string) {
    $(string).find("td").empty();
    $("td:empty").remove();
    $("tr:empty").remove();
}

function addMenu() {
    $.ajax({
        type: "GET",
        url: ajaxUrlRestaurants
    }).done(function (data) {
        var trHTML = '';
        var checkHTML = '';
        clearTable("#listFormRestaurants");
        $.each(data, function (i, item) {
            trHTML += '<tr><td>' + data[i].name + '</td>' +
                '<td>' + '<a onclick=plusRestaurant('+data[i].id+')><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>' + '</td>' +
                '</tr>';
            checkHTML +='<div class="form-check">' +
                '<label><input type="checkbox" name="check"> <span class="label-text">'+data[i].name+'</span></label>' +
                '</div>'
        });
       // $('#listFormRestaurants').append(trHTML);
        $('#listFormRestaurants').append(checkHTML);
        $('#listRestaurants').modal();
    });
}