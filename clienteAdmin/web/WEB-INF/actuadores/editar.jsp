<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar actuador</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputIdFisicoActuador" class="col-sm-2 control-label">Id físico</label>
                    <div class="col-sm-10">
                        <input type="text" name="idFisico" class="form-control" id="inputIdFisicoActuador" placeholder="Id físico del actuador" value="${actuador.idFisico}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDescripcionActuador" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <input type="text" name="descripcion" class="form-control" id="inputDescripcionActuador" placeholder="Descripción del actuador" value="${actuador.descripcion}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectDependenciaActuador" class="col-sm-2 control-label">Dependencia</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="dependencia" id="selectDependenciaActuador">
                            <c:forEach var="d" items="${dependencias}" varStatus="estado">
                                <option value="${d.id}" ${(d.id==actuador.dependencia.id)?'selected="selected"':''}>${d.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputIpActuador" class="col-sm-2 control-label">Dirección IP</label>
                    <div class="col-sm-10">
                        <input type="text" name="ip" class="form-control" id="inputIpActuador" placeholder="Dirección IP (xxx.xxx.xxx.xxx)" value="${actuador.ip}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPuertoActuador" class="col-sm-2 control-label">Puerto</label>
                    <div class="col-sm-10">
                        <input type="text" name="puerto" class="form-control" id="inputPuertoActuador" placeholder="Puerto (0-65534)" value="${actuador.puerto}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectTipoSensor" class="col-sm-2 control-label">Tipo</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="tipo" id="selectTipoSensor">
                            <option value="0" ${(sensor.tipo==0)?'selected="selected"':''}>Otro</option>
                            <option value="1" ${(sensor.tipo==1)?'selected="selected"':''}>Interruptor</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success" name="modificar" value="${actuador.id}">Guardar</button>
                        <a href="${appUrl}/actuadores" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/actuadores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo actuador</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar actuador</a>
            <a href="#" class="list-group-item" onclick="modalEliminarActuador('${actuador.id}', '${actuador.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar actuador</a>
        </div>
        <div class="list-group">
            <c:forEach var="a" items="${actuadores}" varStatus="estado">
                <a href="${appUrl}/actuadores/ver?id=${a.id}" class="list-group-item ${(a.id == actuador.id)?'active':''}">${a.id} - ${a.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->