<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar tarea programada</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputDescripcionTarea" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <input type="text" name="descripcion" class="form-control" id="inputDescripcionTarea" placeholder="Descripción de la tarea programada" value="${tarea.descripcion}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputCronTarea" class="col-sm-2 control-label">Cron</label>
                    <div class="col-sm-10">
                        <input type="text" name="cron" class="form-control" id="inputCronTarea" placeholder="Cron de la tarea programada (Ejemplo cada 20s 0/20 * * * * ?)" value="${tarea.cron}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-labelcol-sm-2">Reglas programadas</label>
                    <c:forEach var="r" items="${tarea.reglasProgramadas}" varStatus="estado">
                        <div class="form-group col-sm-9 pull-right">
                            <input type="text" name="descripcionRegla" class="form-control" id="inputDescripcionRegla" placeholder="Descripción" value="${r.descripcion}"/>
                            <select class="form-control" name="sensores" id="selectSensorTareas">
                                <c:forEach var="s" items="${sensores}" varStatus="estado">
                                    <option value="${s.id}" ${(s.id==r.sensor.id)?'selected="selected"':''}>${s.descripcion}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" name="actuadores" id="selectActuadorTareas">
                                <c:forEach var="a" items="${actuadores}" varStatus="estado">
                                    <option value="${a.id}" ${(a.id==r.actuador.id)?'selected="selected"':''}>${a.descripcion}</option>
                                </c:forEach>
                            </select>
                            <input type="text" name="valorMin" class="form-control" id="inputValorMinRegla" placeholder="Valor mínimo" value="${r.valorMin}"/>
                            <input type="text" name="valorMax" class="form-control" id="inputValorMaxRegla" placeholder="Valor máximo" value="${r.valorMax}"/>
                            <input type="text" name="margenRuido" class="form-control" id="inputMargenRuidoRegla" placeholder="Margen de ruido" value="${r.margenRuido}"/>
                            <input type="text" name="estadoActuador" class="form-control" id="inputEstadoActuadorRegla" placeholder="Estado a establecer el actuador" value="${r.estadoActuador}"/>
                            <button type="button" class="btn btn-default btn-add">+</button>
                        </div>
                    </c:forEach>
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