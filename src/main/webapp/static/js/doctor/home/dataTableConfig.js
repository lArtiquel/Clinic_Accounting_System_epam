$(document).ready(function () {

    // working with DataTable API
    let table = $('#dtEvents').DataTable({
        "pagingType": "full_numbers",
        "order": [[ 0, "asc" ]],
        "dom": "<'row mx-3 my-2 font-italic'<'col-xs-12 col-md-6'<'dtEventsButtonToolbar'>><'col-xs-12 col-md-6'f>>" +
            "<'row'<'col-sm-12'tr>>" +
            "<'row mx-3 my-2'<'col-xs-12 col-md-6'i><'col-xs-12 col-md-6'p>>",
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
        ],
        "select": {"style": 'multiple'},																			// multiple selection
        "processing": true,																							// processing sign on inserting, search and sort
        "responsive": true																							// and lastly it's set responsive property
    });

    // setting content to aforementioned table button's toolbar
    $("div.dtEventsButtonToolbar").html(
        '<div class="d-flex justify-content-center justify-content-xl-start m-2">'+
            '<button id="addNewEventButton" class="btn btn-primary" data-toggle="modal" data-target="#addNewEventModal">Add New Event</button>'+
        '</div>'
    );

});