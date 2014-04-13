<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cliente Táctil</title>
        <script src="libs/jquery/jquery.js"></script>
        <!--<script src="libs/jquery-mobile/jquery-mobile.js"></script>-->
        <script src="libs/twitter-bootstrap/js/bootstrap.js"></script>
        <script src="libs/spinner/spinner.js"></script>

        <link href="libs/twitter-bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="libs/toggle-switch/toggle-switch.css" rel="stylesheet" type="text/css" >

        <script src="js/main.js"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>   
        <div class="row">
            <div id="carousel-example-generic" data-interval="false" class="carousel slide" data-ride="carousel">
                <!-- Indicadores de pantalla actual -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>

                <!-- Definicion de pantallas -->
                <div class="carousel-inner">
                    <div class="item active">

                        <div id="reloj"></div>
                        <div><span id="temperaturaDisplay"></span> ºC</div>

                    </div>
                    <div class="item">

                        <div id="temperaturaControl">
                            <p>TEMPERATURA ACTUAL</p>
                            <div style="font-size:40px;"><span id="temperaturaDisplayControl"></span> ºC</div>

                            <p>TEMPERATURA DESEADA</p>
                            <input id="spinner" type="text" value="21" name="spinner">
                        </div>

                        <div id="lamparasControl">

                            <p>LAMPARAS</p>
                            <div>
                                <label class="switch-light well">
                                    <input type="checkbox">
                                    <span>
                                        Pasillo
                                        <span>Off</span>
                                        <span>On</span>
                                    </span>
                                    <a class="btn btn-primary"></a>
                                </label>
                                <hr>
                                <label class="switch-light well">
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
    </body>
</html>