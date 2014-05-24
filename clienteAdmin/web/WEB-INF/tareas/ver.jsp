<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Ver tarea programada - ${tarea.descripcion}</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputDescripcionTarea" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${tarea.descripcion}</p>
                    </div>
                </div>

                <h3>Reglas programadas</h3>
                <div class="list-group">
                    <c:forEach var="r" items="${tarea.reglasProgramadas}" varStatus="estado">
                        <div class="list-group-item">
                            ${r.id} - ${r.descripcion}

                            <a href="#" class="btn pull-right" onclick="modalEliminarReglaProgramada('${r.id}', '${r.descripcion}')">
                                <span class="glyphicon glyphicon-minus-sign"></span>
                            </a>
                            <a href="/clienteAdmin/reglasprogramadas/editar?id=${r.id}" class="btn pull-right">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                            <a href="/clienteAdmin/reglasprogramadas/ver?id=${r.id}" class="btn pull-right">
                                <span class="glyphicon glyphicon-eye-open"></span>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="${appUrl}/tareas" rol="button" class="btn btn-default">Volver al listado</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/sensores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nueva tarea programada</a>
            <a href="${appUrl}/sensores/editar?id=${tarea.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar tarea programada</a>
            <a href="#" class="list-group-item" onclick="modalEliminarTarea('${tarea.id}', '${tarea.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar tarea programada</a>
        </div>
        <div class="list-group">
            <c:forEach var="t" items="${tareas}" varStatus="estado">
                <a href="${appUrl}/tareas/ver?id=${t.id}" class="list-group-item ${(t.id == tarea.id)?'active':''}">${t.id} - ${t.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->