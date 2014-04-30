<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cliente Táctil</title>
        <script src="libs/jquery/jquery.js"></script>
        <script src="libs/jquery-mobile/jquery-mobile.js"></script>
        <script src="libs/twitter-bootstrap/js/bootstrap.js"></script>
        <script src="libs/spinner/spinner.js"></script>
        <link href="libs/twitter-bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />

        <script src="js/main.js"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>   
        <div class="row">
            <div id="carousel-example-generic" data-interval="false" class="carousel slide" data-ride="carousel">
                <!-- Indicadores de pantalla actual -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>

                <!-- Definicion de pantallas -->
                <div class="carousel-inner">
                    <div class="item active color777">

                        <div id="reloj"></div>
                        <div><span id="temperaturaDisplay">${temperatura.dato}</span> ºC</div>

                    </div>
                    <div class="item">

                        <div id="temperaturaControl">
                            <p>TEMPERATURA ACTUAL</p>
                            <div class="temperaturaDisplay"><span id="temperaturaDisplayControl">${temperatura.dato}</span> ºC</div>

                            <p>TEMPERATURA DESEADA</p>
                            <input id="spinner" type="text" value="${termostato.dato}" name="spinner" disabled="true">
                        </div>

                        <div id="lamparasControl">
                            <p>INTERRUPTORES</p>
                            <a class="glyphicon glyphicon-cog btn btn-default btn-lg" data-toggle="modal" data-target="#config"></a>

                            <div class="botones">
                                <c:forEach var="a" items="${actuadores}" varStatus="estado">
                                    <div>
                                        <label>${a.descripcion}</label>
                                        <div class="toggle-button">
                                            <c:if test="${a.estado == 1}">
                                                <div class="on">
                                                    ON
                                                </div>
                                            </c:if>
                                            <c:if test="${a.estado == 0}">
                                                <div class="off">
                                                    OFF
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>

                    </div>
                </div>

                <!-- Controls -->

                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>

                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>

        <!--Ventana configuracion-->
        <div class="modal fade" id="config" tabindex="-1" role="dialog" aria-labelledby="configLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Configuración</h4>
                    </div>
                    <div class="modal-body">
                        <label>Seleccione la dependencia</label>
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Listado<span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Salón</a></li>
                                    <li><a href="#">Cocina</a></li>
                                    <li><a href="#">Dormitorio</a></li>
                                    <!--                                    <li class="divider"></li>
                                                                        <li><a href="#">Separated link</a></li>-->
                                </ul>
                            </div>
                            <input type="text" class="form-control" name="dependencia">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary">Guardar los cambios</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>