/* FUNCIONES NECESARIAS */

//Variable de URL de la aplicación
var appUrl = "/clienteAdmin/"; //${appUrl};

/**
 * Mostrar modal para eliminar dependencia
 * @param string idItemEliminar Id de la dependencia a eliminar
 * @param string nombreDependencia Nombre de la dependencia a eliminar
 * @returns void
 */
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
 * Mostrar modal para eliminar actuador
 * @param string idItemEliminar Id del actuador a eliminar
 * @param string descripcionActuador Nombre del actuador a eliminar
 * @returns void
 */
function modalEliminarActuador(idItemEliminar, descripcionActuador) {
    $('#descripcionActuadorEliminar').text(descripcionActuador);
    $('#botonEliminarActuador').attr("href", appUrl+"actuadores/eliminar?id="+idItemEliminar);
    $('#modalEliminarActuador').modal();
}

/**
 * Mostrar modal para eliminar regla sensor-actuador
 * @param {type} idItemEliminar
 * @param {type} descripcionRegla
 * @returns {undefined}
 */
function modalEliminarReglaSensorActuador(idItemEliminar, descripcionRegla) {
    $('#descripcionReglaEliminar').text(descripcionRegla);
    $('#botonEliminarRegla').attr("href", appUrl+"reglas/eliminar?id="+idItemEliminar);
    $('#modalEliminarRegla').modal();
}

/**
 * Mostrar modal para eliminar dispositivo
 * @param string idItemEliminar Id del dispositivo a eliminar
 * @param string descripcionDispositivo Descripción del dispositivo a eliminar
 * @returns void
 */
function modalEliminarDispositivo(idItemEliminar, descripcionDispositivo) {
    $('#descripcionDispositivoEliminar').text(descripcionDispositivo);
    $('#botonEliminarDispositivo').attr("href", appUrl+"dispositivos/eliminar?id="+idItemEliminar);
    $('#modalEliminarDispositivo').modal();
}