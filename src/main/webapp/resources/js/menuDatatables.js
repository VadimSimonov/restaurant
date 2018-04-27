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
                "data": "restaurants"
            },
            {
                "data": "meals",
                render: function( data, type, row) {
                    return row.restaurants.meals
                }
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
            checkHTML +='<div id="checkboxes" class="form-check">' +
                '<label><input id="'+data[i].id+'" type="checkbox" name="check"> <span class="label-text">'+data[i].name+'</span></label>' +
                '</div>'
        });
       // $('#listFormRestaurants').append(trHTML);
        $('#listFormRestaurants').append(checkHTML);
        $('#listRestaurants').modal();
    });
}

function addRestaurant() {
    var selected = [];
    $('#checkboxes input:checked').each(function() {
        selected.push($(this).attr('id'));
    });
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data : {
            selected :selected
        },
        success: function () {
            $("#listRestaurants").modal("hide");
            updateTable();
            successNoty("Voted");
        }
    });
}

function updateTable() {
  /*
    $.get(ajaxUrl, function (data) {
        $.each(data, function (i, item) {
            trHTML += '<tr><td>' + data[i].name + '</td>' +
                '<td>' + '<a onclick=plusRestaurant('+data[i].id+')><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>' + '</td>' +
                '</tr>';
        });
        $('#datatableMenu').append(checkHTML);
        });
*/
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (i, item) {
            datatableApi.rows.add(item);
        });
        datatableApi.draw();
    });
  /*
    $.get(ajaxUrl, function (data) {
       // var a = data.restaurants;
        datatableApi.clear().rows.add(data).draw();
    });
*/
}