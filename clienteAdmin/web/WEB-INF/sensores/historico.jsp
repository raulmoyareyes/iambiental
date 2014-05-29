<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Histórico - ${sensor.descripcion}</h1>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <a href="${appUrl}/sensores/ver?id=${sensor.id}" rol="button" class="btn btn-default">Volver al sensor</a>
                </div>
            </div>

            
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/sensores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo sensor</a>
            <a href="${appUrl}/sensores/editar?id=${sensor.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar sensor</a>
            <a href="#" class="list-group-item" onclick="modalEliminarSensor('${sensor.id}', '${sensor.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar sensor</a>
        </div>
        <div class="list-group">
            <c:forEach var="s" items="${sensores}" varStatus="estado">
                <a href="${appUrl}/sensores/ver?id=${s.id}" class="list-group-item ${(s.id == sensor.id)?'active':''}">${s.id} - ${s.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->