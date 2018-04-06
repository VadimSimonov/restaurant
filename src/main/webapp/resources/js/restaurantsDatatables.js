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

function addMeals(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        $('#restaurant_id').val(data.id);
    //    $('#name').val(data.name);
        $('#editMeals').modal();
    });
}

function listMeals(id) {
    $.ajax({
        type: "GET",
        url: ajaxUrl + id
    }).done(function (data) {
        var trHTML = '';
        $.each(data.meals, function (i, item) {
            trHTML += '<tr><td>' + data.meals[i].meal + '</td><td>' + data.meals[i].price + '</td></tr>';
        });
        $('#meals').append(trHTML);


       //   $('#rest_id').val(data.id);
     //   var meal=data.meals;
     //   var meal=data.meals;
     //   var m=JSON.stringify(meal);
    //    $('#meal').val(JSON.stringify(meal));
       // $('#meals').val(m);
     //   $('#meals').val(data);

        $('#listMeals').modal();
    });
}


/*
$.get("list.html", { pageNumber: pn }, function(records) {
        $container = $("#container");
        $container.empty();
        $.each(records, function(index, value) {
            $container.append(value);
        })
    });
 */

function saveMeals() {
    form = $("#detailsFormMeals");
    id=$('#restaurant_id').val();
    $.ajax({
        type: "POST",
        url: ajaxUrl + id + "/meals",
        data : {
            id : $('#meal_id').val(),
            meal : $('#meal_name').val(),
            price : $('#meal_price').val()
          //  restaurants: $('#restaurant_id').val()
        },
        success: function () {
            $("#editMeals").modal("hide");
            updateTable();
            successNoty("Saved");
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