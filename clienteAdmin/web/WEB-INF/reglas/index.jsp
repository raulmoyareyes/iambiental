        <div class="row row-offcanvas row-offcanvas-right">

            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
                </p>
                <div class="row padding1em">
                    <h1>Agregar nueva regla sensor-actuadores</h1>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputNombreRegla" class="col-sm-2 control-label">Nombre</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputNombreRegla" placeholder="Nombre de la regla">
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
                    <a href="#" class="list-group-item active"><span class="glyphicon glyphicon-plus-sign"></span> Insertar</a>
                    <a href="#" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar</a>
                    <a href="#" class="list-group-item"><span class="glyphicon glyphicon-signal"></span> Ver histórico</a>
                    <a href="#" class="list-group-item"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar</a>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item">Regla 1</a>
                    <a href="#" class="list-group-item">Regla 2</a>
                    <a href="#" class="list-group-item">Regla 3</a>
                    <a href="#" class="list-group-item">Regla 4</a>
                    <a href="#" class="list-group-item">Regla 5</a>
                </div>
            </div><!--/span-->
        </div><!--/row-->