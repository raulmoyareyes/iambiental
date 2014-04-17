<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%
            System.out.print("Inteligencia Ambiental");
            %></title>

        <!-- Bootstrap core CSS -->
        <link href="${appUrl}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${appUrl}/css/offcanvas.css" rel="stylesheet">

        <!-- Estilos para este proyecto -->
        <link href="${appUrl}/css/iAmbiental.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
</head>
<body>
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home">iAmbiental</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <!--<li ${(mainMenuOption == 'home')?'class="active"':''} ><a href="home">Home</a></li>-->
                    <li ${(mainMenuOption == 'dependencias')?'class="active"':''} ><a href="${appUrl}/dependencias">Dependencias</a></li>
                    <li ${(mainMenuOption == 'dispositivos')?'class="active"':''} ><a href="${appUrl}/dispositivos">Dispositivos</a></li>
                    <li ${(mainMenuOption == 'sensores')?'class="active"':''} ><a href="${appUrl}/sensores">Sensores</a></li>
                    <li ${(mainMenuOption == 'actuadores')?'class="active"':''} ><a href="${appUrl}/actuadores">Actuadores</a></li>
                    <li ${(mainMenuOption == 'reglas')?'class="active"':''} ><a href="${appUrl}/reglas">Reglas</a></li>
                    <li ${(mainMenuOption == 'tareas')?'class="active"':''} ><a href="${appUrl}/tareas">Tareas</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </div><!-- /.navbar -->
    <div class="container">