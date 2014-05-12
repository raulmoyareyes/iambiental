<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar dispositivo</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputDescripcionDispositivo" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <input type="text" name="descripcion" class="form-control" id="inputDescripcionDispositivo" placeholder="Descripción del dispositivo" value="${dispositivo.descripcion}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputIpDispositivo" class="col-sm-2 control-label">Dirección IP</label>
                    <div class="col-sm-10">
                        <input type="text" name="ip" class="form-control" id="inputIpDispositivo" placeholder="Dirección IP (xxx.xxx.xxx.xxx)" value="${dispositivo.ip}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPuertoDispositivo" class="col-sm-2 control-label">Puerto</label>
                    <div class="col-sm-10">
                        <input type="text" name="puerto" class="form-control" id="inputPuertoDispositivo" placeholder="Puerto (0-65534)" value="${dispositivo.puerto}"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="modificar" value="${dispositivo.id}" class="btn btn-success">Guardar</button>
                        <a href="${appUrl}/dispositivos" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="/clienteAdmin/dispositivos/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo dispositivo</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar dispositivo</a>
            <a href="#" class="list-group-item" onclick="modalEliminarDispositivo('${dispositivo.id}', '${dispositivo.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar dispositivo</a>
        </div>
        <div class="list-group">
            <c:forEach var="d" items="${dispositivos}" varStatus="estado">
                <a href="${appUrl}/dispositivos/ver?id=${d.id}" class="list-group-item ${(d.id == dispositivo.id)?'active':''}">${d.id} - ${d.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->