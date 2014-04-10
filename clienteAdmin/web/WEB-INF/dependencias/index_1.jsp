
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">

    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Dependencias</h1>
            <div class="list-group">
                <c:forEach var="d" items="${dependencias}" varStatus="estado">
                    <div class="list-group-item">
                        ${d.id} - ${d.nombre}
                        <a href="#" class="btn pull-right">
                            <span class="glyphicon glyphicon-minus-sign"></span>
                        </a>
                        <a href="#" class="btn pull-right">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a>
                        <a href="#" class="btn pull-right">
                            <span class="glyphicon glyphicon-eye-open"></span>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="#" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar</a>
            <a href="#" class="list-group-item"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar</a>
        </div>
        <div class="list-group">
            <c:forEach var="d" items="${dependencias}" varStatus="estado">
                <a href="#" class="list-group-item">${d.id} - ${d.nombre}
                
                    <%--Aquí va el dropdown--%>
                    
                </a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->