//Variable que mantiene el estado visible del carrito
// window.onload = function () {
//     let elementToHide = document.getElementById("objeto");
//     elementToHide.style.display = "none";
// }
// //Busca y escodnde el buscador del header
// function handleSearch() {
//     buscador();
//     filter_by_name();
// }
// previene que uses enter en los buscadores para q no de problemas al enviar la información
function preventSubmit(event) {
    if (event.keyCode === 13) {
        event.preventDefault(); // Previene el comportamiento predeterminado (enviar formulario)
    }
}

// function buscador() {
//     let nombre = document.getElementById("nombre");
//     let elementToHide = document.getElementById("objeto");
//
//     if (nombre.value === "") {
//         elementToHide.style.display = "none";
//     } else {
//         elementToHide.style.display = "block";
//     }
// }

function filter_by_name() {
    $.ajax({
        type: "get",
        url: "/usuario/list/filter/",
        data: {
            nombre: $('#nombre').val()
        },
        success: function (htmlRecibido) {
            $('#lista-usuarios').html(htmlRecibido);
        },
        error: function (e) {
            console.log(e);
        }
    });
}

/*function filter_by_name2() {
    $.ajax({
        type: "get",
        url: "/juego/listJuego/filter/",
        data: {
            nombre: $('#nombre').val()
        },
        success: function (htmlRecibido) {
            $('#lista-Juegos').html(htmlRecibido);
        },
        error: function (e) {
            console.log(e);
        }
    });
}*/
/*function filterByName3() {
    var input = document.getElementById("searchInput");
    var filter = input.value.toUpperCase();
    var list = document.getElementById("lista-juegos");
    var items = list.getElementsByClassName("item");

    for (var i = 0; i < items.length; i++) {
        var name = items[i].innerText;
        if (name.toUpperCase().indexOf(filter) > -1) {
            items[i].style.display = "";
        } else {
            items[i].style.display = "none";
        }
    }
}*/
//Filtrar por nombre y por Genero
function filterByName3() {
    var input = document.getElementById("searchInput2");
    var filter = input.value.toUpperCase();
    var tableBody = document.getElementById("tableBody");
    var rows = tableBody.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var nameCell1 = rows[i].querySelector(".item:nth-child(3)");
        var nameCell2 = rows[i].querySelector(".item:nth-child(6)");
        var name1 = nameCell1.innerText.toUpperCase();
        var name2 = nameCell2.innerText.toUpperCase();


        if (name1.includes(filter) || name2.includes(filter)) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}
function filterByGenre() {
    var genreSelect = document.getElementById("genreFilter");
    var selectedGenre = genreSelect.value.toUpperCase();
    var tableBody = document.getElementById("tableBody");
    var rows = tableBody.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var genreCell = rows[i].querySelector(".item:nth-child(6)");
        var genre = genreCell.innerText.toUpperCase();

        if (selectedGenre === "" || genre === selectedGenre) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}
//Exportar a Excel Juegos con la fecha actual de hoy
function exportToExcelJuegos() {
    var table = document.getElementById("lista-juegos");

    // Copiar la tabla excluyendo las columnas "Imagen", "Disponible" y "Edición"
    var filteredTable = table.cloneNode(true);
    var headers = filteredTable.getElementsByTagName("th");

    for (var i = headers.length - 1; i >= 0; i--) {
        var headerText = headers[i].innerText.trim();

        if (
            headerText === "Imagen" ||
            headerText === "Disponible" ||
            headerText === "Edición"
        ) {
            var columnIndex = headers[i].cellIndex;

            for (var j = 0; j < filteredTable.rows.length; j++) {
                filteredTable.rows[j].deleteCell(columnIndex);
            }
        }
    }

    var workbook = XLSX.utils.table_to_book(filteredTable, { sheet: "SheetJS" });

    // Generar el nombre del archivo con la fecha actual
    var currentDate = new Date();
    var filename =
        "Juegos_" +
        currentDate.getFullYear() +
        "-" +
        (currentDate.getMonth() + 1) +
        "-" +
        currentDate.getDate() +
        ".xlsx";

    // Descargar el archivo
    XLSX.writeFile(workbook, filename);
}
//Exportar a Excel Usuario
function exportToExcelUsuarios() {
    console.log("Holi funciono");
    let table = document.getElementById("lista-usuarios");

    // Copiar la tabla excluyendo las columnas "Imagen", "Administrador" y "Edición"
    let filteredTable = table.cloneNode(true);
    let headers = filteredTable.getElementsByTagName("th");

    for (let i = headers.length - 1; i >= 0; i--) {
        let headerText = headers[i].innerText.trim();

        if (
            headerText === "Imagen" ||
            headerText === "Administrador" ||
            headerText === "Edición"
        ) {
            let columnIndex = headers[i].cellIndex;

            for (let j = 0; j < filteredTable.rows.length; j++) {
                filteredTable.rows[j].deleteCell(columnIndex);
            }
        }
    }

    let workbook = XLSX.utils.table_to_book(filteredTable, { sheet: "SheetJS" });
    // Generar el nombre del archivo con la fecha actual
    let currentDate = new Date();
    let filename =
        "Usuarios_" +
        currentDate.getFullYear() +
        "-" +
        (currentDate.getMonth() + 1) +
        "-" +
        currentDate.getDate() +
        ".xlsx";

    // Descargar el archivo
    XLSX.writeFile(workbook, filename);
}

function open_delete(id) {
    $.ajax({
        url: '/usuario/delete/show/' + id,
        success: function (data) {
            $('#paraelmodal').html(data);
            $('#delete-modal').modal('show');
        }
    });
}

/* #3 jQuery mejorado */
/*$(document).ready(() => {
    $("#avatar").change(function () {
        const file = this.files[0];
        if (file) {
            let reader = new FileReader();
            reader.onload = function (event) {
                $("#image-preview").attr("src", event.target.result);
            };
            reader.readAsDataURL(file);
        }
    });
});*/






