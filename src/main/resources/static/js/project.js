var project = {};
var projectId;

$(document).ready(function () {
    $('#table_project').DataTable({
        ajax : {
            url : 'http://localhost:8081/project',
            dataSrc : '',
            beforeSend : addRequestHeader()
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "Project Name",
                "data": "name"
            },
            {
                "name": "Description",
                "data": "description"
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
                          <div class="d-flex justify-content-center">
                              <a class = "btn btn-info btn-sm" type="button" href="#" onclick="detail(${data})"><i class="fas fa-eye"></i></a> | 
                              <a class = "btn btn-warning btn-sm" type="button" href="#" onclick="edit(${data})"><i class="fas fa-edit"></i></a> | 
                              <a class = "btn btn-danger btn-sm" type="button" href="#" onclick="deleteById(${data})"><i class="fas fa-trash-alt"></i></a> 
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
    $('#projectModal').modal('show');
    disabledForm(true);
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8081/project/${id}`,
        dataType: 'json',
        success: (data) => {
            projectId = id;
            project.name = data.name;
            project.description = data.description;
            setForm();
        }
    });
}

function edit(id){
    getById(id);
    $('#projectModal').modal('show');
    disabledForm(false);
}

function create(){
    project = {};
    projectId = null;
    setForm({});
    disabledForm(false);
}

function submit(){
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(projectId){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/project/${projectId}`,
                    contentType: 'application/json',
                    data: JSON.stringify(project),
                    dataType: 'json',
                    success: (data) => {
                        console.log(data);
                        success('project update');
                        $('#table_project').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/project`,
                    contentType: 'application/json',
                    data: JSON.stringify(project),
                    dataType: 'json',
                    success: (data) => {
                        success('project created');
                        $('#table_project').DataTable().ajax.reload(null, false);
                    }
                });
            }
            $('.modal').modal('hide');
        }else{
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    })
}

function deleteById(id){
    question("Do you want to delete this project?", "project deleted", "Delete", ()=>{
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/project/${id}`,
            contentType: 'application/json',
            data: project,
            success: (data) => {
                $('.modal').modal('hide');
                success('project deleted');
                $('#table_project').DataTable().ajax.reload(null, false);
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    });
}

function setValue(){
    project.name = $('#name_project').val();
    project.description = $('#description').val();
}

function setForm(){
    $('#name_project').val(project.name);
    $('#description').val(project.description);
}

function disableForm(isDisable){
    $('#name_project').prop('disabled', isDisable);
    $('#description').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}