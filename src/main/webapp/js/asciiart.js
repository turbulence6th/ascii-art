(function () {
    $('#file').onclick(function () {
        var file = document.getElementById("file").files[0];
        var formData = new FormData();
        formData.append("file", file);
        formData.append("scale", $('#scale').val());
        formData.append("black", $('#black').val());
        formData.append("blue", $('#blue').val());
        formData.append("green", $('#green').val());
        formData.append("cyan", $('#cyan').val());
        formData.append("red", $('#red').val());
        formData.append("magenta", $('#magenta').val());
        formData.append("yellow", $('#yellow').val());
        formData.append("white", $('#white').val());
    });
});