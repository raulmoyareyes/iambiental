<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cliente Táctil</title>
        <script src="libs/jquery/jquery.js"></script>
        <script src="libs/twitter-bootstrap/js/bootstrap.js"></script>

        <link href="libs/twitter-bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="libs/toggle-switch/toggle-switch.css" rel="stylesheet" type="text/css" >
        
        <script src="js/main.js"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>

    </head>

    <body style="position: static;">   
        <div class="row">
            <div align="center" class="col-md-9" role="main">
                <div id="carousel-example-generic" data-interval="false" class="carousel slide" data-ride="carousel">
                    <!-- Indicadores de pantalla actual -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    </ol>

                    <!-- Definicion de pantallas -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img data-src="holder.js/900x500/auto/#666:#666" alt="900x500" 
                                 src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI5MDAiIGhlaWdodD0iNTAwIj48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzY2NiI+PC9yZWN0Pjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjQ1MCIgeT0iMjUwIiBzdHlsZT0iZmlsbDojNjY2O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjU2cHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+OTAweDUwMDwvdGV4dD48L3N2Zz4="/>
                            <div position="relative" align="center" class="carousel-caption">
                                <div id="reloj" style="font-size:40px;"></div>
                                <div style="font-size:40px;"><span id="temperaturaDisplay"></span> ºC</div>

                                </br></br></br></br></br></br>
                            </div>
                        </div>
                        <div class="item">
                            <img data-src="holder.js/900x500/auto/#666:#666" alt="900x500" 
                                 src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI5MDAiIGhlaWdodD0iNTAwIj48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzY2NiI+PC9yZWN0Pjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjQ1MCIgeT0iMjUwIiBzdHlsZT0iZmlsbDojNjY2O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjU2cHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+OTAweDUwMDwvdGV4dD48L3N2Zz4="/>
                            <div class="carousel-caption">

                                <div id="temperaturaControl">
                                    </br>
                                    <p>TEMPERATURA ACTUAL</p>
                                    <div style="font-size:40px;"><span id="temperaturaDisplayControl"></span> ºC</div>
                                    </br>
                                    <p>TEMPERATURA DESEADA</p>
                                    <div class="text-center">
                                        <div style="width:90%;" class="row">
                                            <div class="col-xs-6 col-sm-1"></div>
                                            <div class="col-xs-6 col-sm-3"><button class="btn btn-default">-</button></div>
                                            <div class="col-xs-6 col-sm-4" id="temperaturaElegida" style="font-size:20px;">16ºC</div>
                                            <div class="col-xs-6 col-sm-2"><button class="btn btn-default">+</button></div>
                                        </div>	
                                    </div>
                                </div>

                                <div id="lamparasControl">
                                    </br>
                                    <p>LAMPARAS</p>
                                    <div>
                                        <label class="switch-light well" onclick="">
                                            <input type="checkbox">
                                            <span>
                                                Pasillo
                                                <span>Off</span>
                                                <span>On</span>
                                            </span>
                                            <a class="btn btn-primary"></a>
                                        </label>
                                        <hr>
                                        <label class="switch-light well" onclick="">
                                            <input type="checkbox">
                                            <span>
                                                Salón
                                                <span>Off</span>
                                                <span>On</span>
                                            </span>
                                            <a class="btn btn-primary"></a>
                                        </label>
                                        </hr>
                                    </div>
                                </div>
                                </br></br></br></br></br></br>
                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>