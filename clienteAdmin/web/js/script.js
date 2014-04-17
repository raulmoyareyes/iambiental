/* FUNCIONES NECESARIAS */

/**
 * Mostrar modal para eliminar dependencia
 * @param string idItemEliminar Id de la dependencia a eliminar
 * @param string nombreDependencia Nombre de la dependencia a eliminar
 * @returns void
 */

var appUrl = "/clienteAdmin/";
function modalEliminarDependencia(idItemEliminar, nombreDependencia) {
    $('#nombreDependenciaEliminar').text(nombreDependencia);
    $('#botonEliminarDependencia').attr("href", appUrl+"dependencias/eliminar?id="+idItemEliminar);
    $('#modalEliminarDependencia').modal();
}

/**
 * Mostrar modal para eliminar sensor
 * @param string idItemEliminar Id del sensor a eliminar
 * @param string descripcionSensor Nombre del sensor a eliminar
 * @returns void
 */
function modalEliminarSensor(idItemEliminar, descripcionSensor) {
    $('#descripcionSensorEliminar').text(descripcionSensor);
    $('#botonEliminarSensor').attr("href", appUrl+"sensores/eliminar?id="+idItemEliminar);
    $('#modalEliminarSensor').modal();
}

/**
 * Mostrar modal para eliminar sensor
 * @param string idItemEliminar Id del sensor a eliminar
 * @param string descripcionSensor Nombre del sensor a eliminar
 * @returns void
 */
function modalEliminarActuador(idItemEliminar, descripcionActuador) {
    $('#descripcionActuadorEliminar').text(descripcionActuador);
    $('#botonEliminarActuador').attr("href", appUrl+"actuadores/eliminar?id="+idItemEliminar);
    $('#modalEliminarActuador').modal();
}