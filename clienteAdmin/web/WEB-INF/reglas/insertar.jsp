<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Insertar nueva regla sensor-actuador</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputDescripcionRegla" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <input type="text" name="descripcionRegla" class="form-control" id="inputDescripcionRegla" placeholder="Descripción de la regla sensor-actuador"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectSensorRegla" class="col-sm-2 control-label">Sensor</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="sensorRegla" id="selectSensorRegla">
                            <c:forEach var="s" items="${sensores}" varStatus="estado">
                                <option value="${s.id}">${s.descripcion}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectActuadorRegla" class="col-sm-2 control-label">Actuador</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="actuadorRegla" id="selectActuadorRegla">
                            <c:forEach var="a" items="${actuadores}" varStatus="estado">
                                <option value="${a.id}">${a.descripcion}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="${appUrl}/actuadores" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nueva regla</a>
        </div>
        <div class="list-group">
            <c:forEach var="r" items="${reglasSensorActuador}" varStatus="estado">
                <a href="${appUrl}/reglas/ver?id=${r.id}" class="list-group-item">${r.id} - ${r.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->