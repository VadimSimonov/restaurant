var ajaxUrl = "ajax/admin/menu/";
var ajaxUrlRestaurants = "ajax/admin/restaurants";
var datatableApi;
var id;

$(function () {
    var referrer = document.referrer;
    if (referrer.indexOf("profile") > -1) {
        successNoty("common.saved");
    }
    datatableApi = $("#datatableMenu").DataTable({
            'ajax'       : {
            "url"    : ajaxUrl,
            "dataSrc": function (json) {
            var return_data = [];
            var restaurants=json[0].restaurants;
            for (var i = 0; i < restaurants.length; i++) {
                var rating=0;
                for (var j = 0;j <json[0].restaurants[i].vote.length ; j++) {
                    rating+=restaurants[i].vote[j].vote;
                }
                return_data.push({
                    'date': json[0].date,
                    'restaurants_id': restaurants[i].id,
                    'restaurants': restaurants[i].name,
                    'meals':"<td><a onclick='listMeals(" + restaurants[i].id + ")'><span class='glyphicon glyphicon-list' aria-hidden='true'></span></a></td>",
                    'vote': rating
                })
            }
        return return_data;
    },
                "paging": false,
                "info": true
},
    "columns"    : [
        {'data': 'date'},
        {'data': 'restaurants'},
        {'data': 'meals'},
        {'data': 'vote'
        },

        {
        "orderable": false,
        "defaultContent": "",
        "render": renderPlusBtn
        },
        {
            "orderable": false,
            "defaultContent": "",
            "render": renderMinusBtn
        }
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
            successNoty("common.voted");
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
            successNoty("common.voted");
        }
    });
}

function clearTable(string) {
    $(string).find("td").empty();
    $("td:empty").remove();
    $("tr:empty").remove();

    $(string).find("div").empty();
    $("div:empty").remove();
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
            checkHTML +='<div id="checkboxes" class="form-check">' +
                '<label><input id="'+data[i].id+'" type="checkbox" name="check"> <span class="label-text">'+data[i].name+'</span></label>' +
                '</div>'
        });
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
            successNoty("common.saved");
        }
    });
}

function renderPlusBtn(data, type, row) {
    if (type === "display") {
        return "<td><a onclick='voitePlus("+row.restaurants_id+","+1+")'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span></a></td>"
    }
}

function renderMinusBtn(data, type, row) {
    if (type === "display") {
        return "<td><a onclick='voiteMinus("+row.restaurants_id+","+-1+")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></a></td>"
    }
}

function updateTable() {
        datatableApi.ajax.reload();
}