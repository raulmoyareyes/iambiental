        <div class="row row-offcanvas row-offcanvas-right">

            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
                </p>
                <div class="row">
                    <h1>Agregar nuevo sensor</h1>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputNombreSensor" class="col-sm-2 control-label">Nombre</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputNombreSensor" placeholder="Nombre del sensor">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputTipoLectura" class="col-sm-2 control-label">Tipo de lectura</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputTipoLectura">
                                    <option>Todo-nada (binaria)</option>
                                    <option>Continua</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="inputNaturalezaLectura" class="col-sm-2 control-label">Naturaleza de la lectura</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputTipoLectura">
                                    <option>Pulsador</option>
                                    <option>Temperatura</option>
                                    <option>Interruptor</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="selectUbicacionSensor" class="col-sm-2 control-label">Dependencia</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="selectUbicacionSensor" name="ubicacionDispositivo">
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
                            <label for="selectDispositivoSensor" class="col-sm-2 control-label">Dispositivo</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="selectDispositivoSensor">
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
                    <a href="#" class="list-group-item">Pulsador Salón</a>
                    <a href="#" class="list-group-item">Pulsador Cocina</a>
                    <a href="#" class="list-group-item">Pulsador dormitorio principal</a>
                    <a href="#" class="list-group-item">Pulsador dormitorio individual</a>
                    <a href="#" class="list-group-item">Sensor temperatura Salón</a>
                    <a href="#" class="list-group-item">Sensor temperatura Cocina</a>
                    <a href="#" class="list-group-item">Sensor temperatura dormitorio principal</a>
                    <a href="#" class="list-group-item">Sensor temperatura dormitorio individual</a>
                </div>
            </div><!--/span-->
        </div><!--/row-->