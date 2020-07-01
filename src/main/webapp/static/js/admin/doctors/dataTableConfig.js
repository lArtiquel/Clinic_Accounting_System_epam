$(document).ready(function () {

    // working with DataTable API
    let doctorsTable = $('#dtDoctors').DataTable({
        "pagingType": "full_numbers",
        "order": [[ 0, "asc" ]],
        "dom": "<'row mx-3 my-2 font-italic'<'col-xs-12 col-md-6'<'dtDoctorsButtonToolbar'>><'col-xs-12 col-md-6'f>>" +
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
    $("div.dtDoctorsButtonToolbar").html(
        '<div class="d-flex justify-content-center justify-content-xl-start">'+
            '<button id="addNewDoctorButton" class="btn btn-primary m-2" data-toggle="modal" data-target="#addNewDoctorModal">Add doctor</button>'+
            '<form action="DeleteDoctor" method="post">' +
                '<input type="hidden" id="docIDToDelete" name="docID"/>' +
                '<button type="submit" id="deleteDoctorButton" class="btn btn-danger m-2 disabled" disabled>Delete doctor</button>' +
            '</form>'+
        '</div>'
    );

    // setting custon action on select and deselect row
    doctorsTable
        .on( 'deselect', function ( e, dt, type, indexes ) {
            if ( type === 'row' ) {
                // changing button style
                $("#deleteDoctorButton").addClass('disabled');
                // changing button property
                $('#deleteDoctorButton').prop('disabled', true);
            }
        } )
        .on( 'select', function ( e, dt, type, indexes ) {
            if ( type === 'row' ) {
                let selectedDoctor = doctorsTable.rows(indexes).data().toArray();
                // set eventID to know which one to delete
                document.getElementById("docIDToDelete").value = selectedDoctor[0][0];
                // changing button style
                $("#deleteDoctorButton").removeClass('disabled');
                // changing button property
                $('#deleteDoctorButton').prop('disabled', false);
            }
        } );

});