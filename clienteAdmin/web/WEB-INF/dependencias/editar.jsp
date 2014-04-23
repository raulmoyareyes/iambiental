<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Editar dependencia</h1>

            <form class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputNombreDependencia" class="col-sm-2 control-label">Ubicación</label>
                    <div class="col-sm-10">
                        <input type="text" name="inputNombreDependencia" class="form-control" id="inputNombreDependencia" placeholder="Nombre de la dependencia" value="${dependencia.nombre}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="textareaDescripcionDependencia" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <input type="text" name="textareaDescripcionDependencia" class="form-control" id="textareaDescripcionDependencia" placeholder="Descripción de la dependencia" value="${dependencia.descripcion}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="/clienteAdmin/dependencias/ver?id=${dependencia.id}" rol="button" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>

        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="/clienteAdmin/dependencias/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nueva dependencia</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar dependencia</a>
            <a href="#" class="list-group-item" onclick="modalEliminarDependencia('${dependencia.id}', '${dependencia.nombre}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar dependencia</a>
        </div>
        <div class="list-group">
            <c:forEach var="d" items="${dependencias}" varStatus="estado">
                <a href="/clienteAdmin/dependencias/ver?id=${d.id}" class="list-group-item ${(d.id == dependencia.id)?'active':''}">${d.id} - ${d.nombre}</a>
            </c:forEach>
        </div>

    </div><!--/span-->
</div><!--/row-->