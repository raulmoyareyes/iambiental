/* FUNCIONES NECESARIAS */

/**
 * Mostrar modal para eliminar dependencia
 * @param string idItemEliminar Id de la dependencia a eliminar
 * @param string nombreDependencia Nombre de la dependencia a eliminar
 * @returns void
 */
function modalEliminarDependencia(idItemEliminar, nombreDependencia) {
    $('#nombreDependenciaEliminar').text(nombreDependencia);
    $('#botonEliminarDependencia').attr("href", "/clienteAdmin/dependencias/eliminar?id="+idItemEliminar);
    $('#modalEliminarDependencia').modal();
}