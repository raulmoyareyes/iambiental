        <div class="row row-offcanvas row-offcanvas-right">

            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
                </p>
                <div class="row">
                    <h1>Agregar nuevo actuador</h1>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputNombreActuador" class="col-sm-2 control-label">Nombre</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputNombreActuador" placeholder="Nombre del actuador">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputTipoActuador" class="col-sm-2 control-label">Tipo de actuador</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputTipoActuador">
                                    <option>Todo-nada (binario)</option>
                                    <option>Continuo</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="inputNaturalezaActuador" class="col-sm-2 control-label">Naturaleza del actuador</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputNaturalezaActuador">
                                    <option>Iluminación</option>
                                    <option>Temperatura</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="selectUbicacionActuador" class="col-sm-2 control-label">Dependencia</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="selectUbicacionActuador" name="ubicacionActuador">
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
                            <label for="selectDispositivoActuador" class="col-sm-2 control-label">Dispositivo</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="selectDispositivoActuador">
                                    <option>Arduino salón</option>
                                    <option>Arduino cocina</option>
                                    <option>Arduino pasillo</option>
                                    <option>Beagle</option>
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
                    <a href="#" class="list-group-item"><span class="glyphicon glyphicon-signal"></span> Ver histórico</a>
                    <a href="#" class="list-group-item"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar</a>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item">Luz salón</a>
                    <a href="#" class="list-group-item">Luz cocina</a>
                    <a href="#" class="list-group-item">Luz baño</a>
                    <a href="#" class="list-group-item">Luz dormitorio principal</a>
                    <a href="#" class="list-group-item">Luz dormitorio individual</a>
                    <a href="#" class="list-group-item">Luz pasillo</a>
                    <a href="#" class="list-group-item">Calefacción salón</a>
                    <a href="#" class="list-group-item">Calefacción cocina</a>
                    <a href="#" class="list-group-item">Calefacción baño</a>
                    <a href="#" class="list-group-item">Calefacción dormitorio principal</a>
                    <a href="#" class="list-group-item">Calefacción dormitorio individual</a>
                    <a href="#" class="list-group-item">Calefacción pasillo</a>
                </div>
            </div><!--/span-->
        </div><!--/row-->