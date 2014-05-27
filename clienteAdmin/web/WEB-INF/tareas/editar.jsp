<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar tarea programada</h1>

            <form class="form-horizontal" role="form">

                ////eeeee

                <div class="form-group">
                    <label for="selectTipoSensor" class="col-sm-2 control-label">Tipo</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="tipo" id="selectTipoSensor">
                            <option value="0" ${(sensor.tipo==0)?'selected="selected"':''}>Otro</option>
                            <option value="1" ${(sensor.tipo==1)?'selected="selected"':''}>Temperatura</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="modificar" class="btn btn-success" value="${tarea.id}">Guardar</button>
                        <a href="${appUrl}/tareas" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="/clienteAdmin/sensores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nueva tarea programada</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar tarea programada</a>
            <a href="#" class="list-group-item" onclick="modalEliminarSensor('${sensor.id}', '${sensor.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar tarea programada</a>
        </div>
        <div class="list-group">
            <c:forEach var="t" items="${tareas}" varStatus="estado">
                <a href="${appUrl}/tareas/ver?id=${t.id}" class="list-group-item ${(t.id == tarea.id)?'active':''}">${t.id} - ${t.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->