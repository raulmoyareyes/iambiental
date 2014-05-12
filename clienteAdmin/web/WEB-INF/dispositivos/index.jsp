
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">

    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">

            <c:if test="${eliminado != null}">
                <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <strong>Yeah!</strong> El dispositivo "${eliminado}" ha sido eliminado del sistema.
                </div>
            </c:if>
            
            <h1>Dispositivos</h1>
            <div class="list-group">
                <c:forEach var="d" items="${dispositivos}" varStatus="estado">
                    <div class="list-group-item">
                        ${d.id} - ${d.descripcion}
                        
                        <a href="#" class="btn pull-right" onclick="modalEliminarDispositivo('${d.id}', '${d.descripcion}')">
                            <span class="glyphicon glyphicon-minus-sign"></span>
                        </a>
                        <a href="/clienteAdmin/dispositivos/editar?id=${d.id}" class="btn pull-right">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a>
                        <a href="/clienteAdmin/dispositivos/ver?id=${d.id}" class="btn pull-right">
                            <span class="glyphicon glyphicon-eye-open"></span>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/dispositivos/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo dispositivo</a>
        </div>
    </div><!--/span-->
</div><!--/row-->

