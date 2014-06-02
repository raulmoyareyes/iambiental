<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Ver actuador - ${actuador.descripcion}</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputPuertoActuador" class="col-sm-2 control-label">Id físico</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${actuador.idFisico}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDescripcionActuador" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${actuador.descripcion}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectDependenciaActuador" class="col-sm-2 control-label">Dependencia</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${actuador.dependencia.nombre}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputIpActuador" class="col-sm-2 control-label">Dirección IP</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${actuador.ip}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPuertoActuador" class="col-sm-2 control-label">Puerto</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${actuador.puerto}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputTipoActuador" class="col-sm-2 control-label">Tipo</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${(actuador.tipo==0)?'Otro':'Interruptor'}</p>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="${appUrl}/actuadores" rol="button" class="btn btn-default">Volver al listado</a>
                        <a href="${appUrl}/actuadores/historico?id=${actuador.id}" rol="button" class="btn btn-default"><span class="glyphicon glyphicon-stats"></span> Ver histórico</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/actuadores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo actuador</a>
            <a href="${appUrl}/actuadores/editar?id=${actuador.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar actuador</a>
            <a href="#" class="list-group-item"><span class="glyphicon glyphicon-signal"></span> Ver histórico</a>
            <a href="#" class="list-group-item" onclick="modalEliminarActuador('${actuador.id}', '${actuador.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar actuador</a>
        </div>
        <div class="list-group">
            <c:forEach var="a" items="${actuadores}" varStatus="estado">
                <a href="${appUrl}/actuadores/ver?id=${a.id}" class="list-group-item ${(a.id == actuador.id)?'active':''}">${a.id} - ${a.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->