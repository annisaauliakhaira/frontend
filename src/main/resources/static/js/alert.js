var Toast;

function success(message){
    toast();

    Toast.fire({
        icon : 'success',
        title : message
    })
}

function error(message) {
    toast();
    Toast.fire({
        icon: 'error',
        title: message
    });
}

function question(question, message, confirmText, action) {
    Swal.fire({
        title: question,
        showCancelButton: true,
        confirmButtonText: `${confirmText}`,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            action();
        }
    });
}

function toast() {
    Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })
}