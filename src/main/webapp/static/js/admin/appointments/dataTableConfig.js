$(document).ready(function () {

    // working with DataTable API
    $('#dtAppointments').DataTable({
        "pagingType": "full_numbers",
        "order": [[ 0, "asc" ]],
        "dom": "<'row mx-3 my-2 font-italic'<'col-xs-12 col-md-6'<'dtAppointmentsButtonToolbar'>><'col-xs-12 col-md-6'f>>" +
            "<'row'<'col-12'tr>>" +
            "<'row mx-3 my-2'<'col-xs-12 col-md-6'i><'col-xs-12 col-md-6'p>>",
        "select": {"style": 'single'},
        "processing": true,
        "responsive": true
    });

    // setting content to aforementioned table button's toolbar
    $("div.dtAppointmentsButtonToolbar").html(
        '<div class="d-flex justify-content-center justify-content-xl-start">'+
            '<button id="deleteButton" class="btn btn-danger m-2" data-toggle="modal" data-target="#deleteAppointmentsModal">Delete by date</button>'+
        '</div>'
    );

});