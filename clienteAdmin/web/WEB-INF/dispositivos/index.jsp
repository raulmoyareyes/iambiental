        <div class="row row-offcanvas row-offcanvas-right">

            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
                </p>
                <div class="row padding1em">
                    <h1>Agregar nuevo dispositivo</h1>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputNombreDispositivo" class="col-sm-2 control-label">Nombre</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputNombreDispositivo" placeholder="Nombre del dispositivo">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputTipoDispositivo" class="col-sm-2 control-label">Tipo</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputTipoDispositivo">
                                    <option>Arduino</option>
                                    <option>Beagle</option>
                                    <option>Raspberry</option>
                                    <option>Otro</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputUbicacionDispositivo" class="col-sm-2 control-label">Ubicación</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputUbicacionDispositivo" placeholder="Ubicación del dispositivo">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="selectDependenciaDispositivo" class="col-sm-2 control-label">Dependencia</label>
                            <div class="col-sm-10">
                                 <select class="form-control" id="inputUbicacionDispositivo" name="ubicacionDispositivo">
                                    <option>Salón</option>
                                    <option>Cocina</option>
                                    <option>Pasillo</option>
                                    <option>Dormitorio principal</option>
                                    <option>Dormitorio individual</option>
                                    <option>Pasillo</option>
                                    <option>Piscina</option>
                                </select>
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
                    <a href="#" class="list-group-item"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar</a>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item">Arduino salón</a>
                    <a href="#" class="list-group-item">Arduino cocina</a>
                    <a href="#" class="list-group-item">Arduino pasillo</a>
                    <a href="#" class="list-group-item">Beagle</a>
                </div>
            </div><!--/span-->
        </div><!--/row-->