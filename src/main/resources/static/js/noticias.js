const noticias = Array.from(document.getElementsByClassName('noticia'));
const anteriorBtn = document.getElementById('anteriorBtn');
const siguienteBtn = document.getElementById('siguienteBtn');

let indiceActual = 0;
const noticiasPorPagina = 2;

function mostrarNoticias() {
    noticias.forEach((noticia, index) => {
        if (index >= indiceActual && index < indiceActual + noticiasPorPagina) {
            noticia.classList.add('mostrar');
        } else {
            noticia.classList.remove('mostrar');
        }
    });

    anteriorBtn.disabled = indiceActual === 0;
    siguienteBtn.disabled = indiceActual >= noticias.length - noticiasPorPagina;
}

siguienteBtn.addEventListener('click', () => {
    indiceActual += noticiasPorPagina;
    mostrarNoticias();
});

anteriorBtn.addEventListener('click', () => {
    indiceActual -= noticiasPorPagina;
    mostrarNoticias();
});

mostrarNoticias();
