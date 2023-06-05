// Obtener el elemento del contador de página
const contadorPagina = document.getElementById('contadorPagina');

// Obtener la ruta actual
const rutaActual = window.location.pathname;

// Extraer el número de página de la ruta actual
const numeroPagina = obtenerNumeroPagina(rutaActual);

// Actualizar el contenido del contador de página
contadorPagina.textContent = `Página ${numeroPagina}`;

// Función para extraer el número de página de la ruta
function obtenerNumeroPagina(ruta) {
    // Obtener el último segmento de la ruta
    const segments = ruta.split('/');
    const ultimoSegmento = segments[segments.length - 1];

    // Extraer el número de página de la cadena
    const numeroPagina = ultimoSegmento.replace('.html', '');

    return numeroPagina;
}
