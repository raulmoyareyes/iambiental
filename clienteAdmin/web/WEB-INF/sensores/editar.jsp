<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Men� lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar sensor</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputIdFisicoSensor" class="col-sm-2 control-label">Id f�sico</label>
                    <div class="col-sm-10">
                        <input type="text" name="idFisico" class="form-control" id="inputIdFisicoSensor" placeholder="Id f�sico del sensor" value="${sensor.idFisico}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDescripcionSensor" class="col-sm-2 control-label">Descripci�n</label>
                    <div class="col-sm-10">
                        <input type="text" name="descripcion" class="form-control" id="inputDescripcionSensor" placeholder="Descripci�n del actuador" value="${sensor.descripcion}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectDependenciaSensor" class="col-sm-2 control-label">Dependencia</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="dependencia" id="selectDependenciaSensor">
                            <c:forEach var="d" items="${dependencias}" varStatus="estado">
                                <option value="${d.id}" ${(d.id==sensor.dependencia.id)?'selected="selected"':''}>${d.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputIpSensor" class="col-sm-2 control-label">Direcci�n IP</label>
                    <div class="col-sm-10">
                        <input type="text" name="ip" class="form-control" id="inputIpSensor" placeholder="Direcci�n IP (xxx.xxx.xxx.xxx)" value="${sensor.ip}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPuertoSensor" class="col-sm-2 control-label">Puerto</label>
                    <div class="col-sm-10">
                        <input type="text" name="puerto" class="form-control" id="inputPuertoSensor" placeholder="Puerto (0-65534)" value="${sensor.puerto}"/>
                    </div>
                </div>

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
                        <button type="submit" name="modificar" class="btn btn-success" value="${sensor.id}">Guardar</button>
                        <a href="${appUrl}/sensores" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="/clienteAdmin/sensores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo sensor</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar sensor</a>
            <a href="#" class="list-group-item" onclick="modalEliminarSensor('${sensor.id}', '${sensor.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar sensor</a>
        </div>
        <div class="list-group">
            <c:forEach var="s" items="${sensores}" varStatus="estado">
                <a href="${appUrl}/sensores/ver?id=${s.id}" class="list-group-item ${(s.id == sensor.id)?'active':''}">${s.id} - ${s.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->