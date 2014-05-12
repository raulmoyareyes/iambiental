<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Ver dispositivo - ${dispositivo.descripcion}</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputDescripcionDispositivo" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${dispositivo.descripcion}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputIpDispositivo" class="col-sm-2 control-label">Dirección IP</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${dispositivo.ip}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPuertoDispositivo" class="col-sm-2 control-label">Puerto</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${dispositivo.puerto}</p>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="${appUrl}/dispositivos" rol="button" class="btn btn-default">Volver al listado</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/dispositivos/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo dispositivo</a>
            <a href="${appUrl}/dispositivos/editar?id=${dispositivo.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar dispositivo</a>
            <a href="#" class="list-group-item" onclick="modalEliminarDispositivo('${dispositivo.id}', '${dispositivo.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar dispositivo</a>
        </div>
        <div class="list-group">
            <c:forEach var="d" items="${dispositivos}" varStatus="estado">
                <a href="${appUrl}/dispositivos/ver?id=${d.id}" class="list-group-item ${(d.id == dispositivo.id)?'active':''}">${d.id} - ${d.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->