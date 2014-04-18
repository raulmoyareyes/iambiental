<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar regla sensor-actuador</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${reglaSensorActuador.descripcion}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Sensor</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${reglaSensorActuador.sensor.descripcion}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Actuador</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${reglaSensorActuador.actuador.descripcion}</p>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="${appUrl}/reglas" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/reglas/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nueva regla</a>
            <a href="${appUrl}/reglas/editar?id=${reglaSensorActuador.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar regla</a>
            <a href="#" class="list-group-item" onclick="modalEliminarReglaSensorActuador('${reglaSensorActuador.id}', '${reglaSensorActuador.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar regla</a>
        </div>
        <div class="list-group">
            <c:forEach var="r" items="${reglasSensorActuador}" varStatus="estado">
                <a href="${appUrl}/reglas/ver?id=${r.id}" class="list-group-item ${(r.id==reglaSensorActuador.id)?'active':''}">${r.id} - ${r.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->