var employee = {};
var department = {};
var empId;

$(document).ready(function () {
    $('#table_employee').DataTable({
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
                "name": "Name",
                "data": null,
                render : function (data, type, row){
                    return row.firstName + ' ' + row.lastName;
                }
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
                        result += index==1 +'<br>' ?  data.name : '<span class="center">'+data.name+'<span class="center">'+'<br>';
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
                  <a class = "btn btn-danger btn-sm" type="button" href="#" onclick="deleteById(${data})"><i class="fas fa-trash-alt"></i></a> 
              </div>
            `;
                }
            }
        ]
    });
});

function detail(id) {
    getById(id);
    $('#employeeModal').modal('show');
    disabledForm(true);
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8081/employee/${id}`,
        dataType: 'json',
        success: (data) => {
            empId = id;
            employee.firstName = data.firstName;
            setForm();
        }
    });
}

function deleteById(id) {
    question("Do you want to delete this employee?", "employee deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/employee/${id}`,
            contentType: 'application/json',
            data: employee,
            success: (data) => {
                $('.modal').modal('hide');
                success('employee deleted');
                $('#table_employee').DataTable().ajax.reload(null, false);
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    });
}

function setValue() {
    department.name = $('#name_dept').val();
}

function setForm() {
    $('#first_name').val(employee.firstName);
    $('#last_name').val(employee.lastName);
    $('#email').val(employee.email);
    $('#address').val(employee.address);
    $('#departmentSelect').val(employee.address);
}

function disabledForm(isDisable) {
    $('#first_name').prop('disabled', isDisable);
    $('#last_name').prop('disabled', isDisable);
    $('#email').prop('disabled', isDisable);
    $('#address').prop('disabled', isDisable);
    $('#name_dept').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}