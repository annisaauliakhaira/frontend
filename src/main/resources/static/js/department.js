/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var department = {};
var depId;

$(document).ready(function () {
    var table= $('#table_department').DataTable({
        ajax : {
            url : 'http://localhost:8081/department',
            dataSrc : ''
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "Department Name",
                "data": "name"
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <a href="#" onclick="edit(${data})"><i class="fas fa-edit"></i></a> | 
                  <a href="#" onclick="deleteById(${data})"><i class="fas fa-trash-alt"></i></a> 
              </div>
            `;
                }
            }
        ]
    });
    submit();
});

function detail(id) {
    getById(id);
    disabledForm(true);
}

function getById(id) {
    $.ajax({
        url: `/department/${id}`,
        dataType: 'json',
        success: (data) => {
            depId = id;
            department.name = data.name;
            setForm();
        }
    });
}

function create() {
    department={};
    depId=null;
    setForm({});
    disabledForm(false);
}

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(depId){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/department/${depId}` ,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        console.log(data);
                        success('department update');
                        $('#table_department').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
                var _this = this;
                $.ajax({
                    type: "POST",
                    url: `/department/create`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        success('department created');
                        $('#table_department').DataTable().ajax.reload(null, false);
                    }
                });
            }
            $('.modal').modal('hide');
            // setInterval('refreshPage()', 1000);
        }else{
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    })
}

function setValue() {
    department.name = $('#name_dept').val();
}
function edit(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(false);
}

function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `/department/${id}`,
            contentType: 'application/json',
            data: department,
            success: (data) => {
                $('.modal').modal('hide');
                success('department deleted');
                $('#table_department').DataTable().ajax.reload(null, false);
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    });
}

function setForm() {
    $('#name_dept').val(department.name);
}

function disabledForm(isDisable) {
    $('#name_dept').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}