$(document).ready(function () {

    // working with DataTable API
    let eventsTable = $('#dtEvents').DataTable({
        "pagingType": "full_numbers",
        "order": [[ 0, "asc" ]],
        "dom": "<'row mx-3 my-2 font-italic'<'col-xs-12 col-md-6'<'dtEventsButtonToolbar'>><'col-xs-12 col-md-6'f>>" +
            "<'row'<'col-12'tr>>" +
            "<'row mx-3 my-2'<'col-xs-12 col-md-6'i><'col-xs-12 col-md-6'p>>",
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
        ],
        "select": {"style": 'single'},
        "processing": true,
        "responsive": true
    });

    // setting content to aforementioned table button's toolbar
    $("div.dtEventsButtonToolbar").html(
        '<div class="d-flex justify-content-center justify-content-xl-start">'+
            '<button id="addNewEventButton" class="btn btn-primary m-2" data-toggle="modal" data-target="#addNewEventModal">Add event</button>'+
            '<form action="DeleteEvent" method="post">' +
                '<input type="hidden" id="eventIDToDelete" name="eventID"/>' +
                '<button type="submit" id="deleteEventButton" class="btn btn-danger m-2 disabled" disabled >Delete event</button>' +
            '</form>'+
        '</div>'
    );

    // setting custon action on select and deselect row
    eventsTable
        .on( 'deselect', function ( e, dt, type, indexes ) {
            if ( type === 'row' ) {
                // changing button style
                $("#deleteEventButton").addClass('disabled');
                // changing button property
                $('#deleteEventButton').prop('disabled', true);
            }
        } )
        .on( 'select', function ( e, dt, type, indexes ) {
            if ( type === 'row' ) {
                let selectedEvent = eventsTable.rows(indexes).data().toArray();
                // set eventID to know which one to delete
                document.getElementById("eventIDToDelete").value = selectedEvent[0][0];
                // changing button style
                $("#deleteEventButton").removeClass('disabled');
                // changing button property
                $('#deleteEventButton').prop('disabled', false);
            }
        } );

});