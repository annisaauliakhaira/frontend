var employee = {};
var empId;

$(document).ready(function () {
    var table= $('#table_employee').DataTable({
        ajax : {
            url : 'http://localhost:8081/employee',
            dataSrc : ''
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "First Name",
                "data": "firstName"
            },
            {
                "name": "Last Name",
                "data": "lastName"
            },
            {
                "name": "Email",
                "data": "email"
            },
            {
                "name": "Address",
                "data": "address"
            },
            {
                "name": "Department",
                "data" : null,
                render : function(data, type, row) {
                    return row.department ? row.department.name : null;
                }
            },
            {
                "name": "Username",
                "data" : null,
                render : function(data, type, row) {
                    return row.user ? row.user.username : null;
                }
            },
            {
                "name": "Project",
                "data" : null,
                render : function(data, type, row) {
                    var result = "";
                    row.projects.forEach((data, index) => {
                        result += index==1 ?  data.name : ' | '+data.name+' | ';
                    });
                    return result;
                }
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <a class = "btn btn-info btn-sm" type="button" href="#" onclick=""><i class="fas fa-eye"></i></a> | 
                  <a class = "btn btn-warning btn-sm" type="button" href="#" onclick=""><i class="fas fa-edit"></i></a> | 
                  <a class = "btn btn-danger btn-sm" type="button" href="#" onclick=""><i class="fas fa-trash-alt"></i></a> 
              </div>
            `;
                }
            }
        ]
    });
    submit();
});