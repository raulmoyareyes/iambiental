<div class="row row-offcanvas row-offcanvas-right">

    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row">
            <h1>Editar dependencia - Dormitorio principal</h1>

            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="inputNombreDependencia" class="col-sm-2 control-label">Nombre</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputNombreDependencia" placeholder="Nombre de la dependencia">
                    </div>
                </div>
                <div class="form-group">
                    <label for="textAreaDescripcionDependencia" class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="textAreaDescripcionDependencia" placeholder="Descripción de la dependencia"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Enviar</button>
                        <button type="reset" class="btn btn-primary">Limpiar formulario</button>
                        <button type="button" class="btn btn-danger">Cancelar</button>
                    </div>
                </div>
            </form>

        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="#" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar</a>
            <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-edit"></span> Editar</a>
            <a href="#" class="list-group-item"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar</a>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item">Salón</a>
            <a href="#" class="list-group-item">Cocina</a>
            <a href="#" class="list-group-item">Baño</a>
            <a href="#" class="list-group-item active">Dormitorio principal</a>
            <a href="#" class="list-group-item">Dormitorio individual</a>
            <a href="#" class="list-group-item">Pasillo</a>
            <a href="#" class="list-group-item">Piscina</a>
        </div>
    </div><!--/span-->
</div><!--/row-->