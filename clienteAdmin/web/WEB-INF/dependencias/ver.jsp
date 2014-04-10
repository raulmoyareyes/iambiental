<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Ver dependencia - ${dependencia.nombre}</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputNombreDependencia" class="col-sm-2 control-label">Ubicación</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${dependencia.nombre}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${dependencia.descripcion}</p>
                    </div>
                </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a href="/clienteAdmin/dependencias" rol="button" class="btn btn-default">Volver al listado</a>
                        </div>
                    </div>
            </form>

        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="/clienteAdmin/dependencias/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nueva dependencia</a>
            <a href="/clienteAdmin/dependencias/editar?id=${dependencia.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar dependencia</a>
            <a href="#" class="list-group-item"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar dependencia</a>
        </div>
        <div class="list-group">
            <c:forEach var="d" items="${dependencias}" varStatus="estado">
                <a href="/clienteAdmin/dependencias/ver?id=${d.id}" class="list-group-item ${(d.id == dependencia.id)?'active':''}">${d.id} - ${d.nombre}</a>
            </c:forEach>
        </div>

    </div><!--/span-->
</div><!--/row-->