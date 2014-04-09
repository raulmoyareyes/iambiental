<!DOCTYPE html>
<html lang="es">
  <head>
        <title>Cliente Táctil</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery-1.7.2.min.js"></script>
		<script src="js/jquery-1.9.0.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap-transition.js"></script>
		<!-- Toggle Switch -->
		<link rel="stylesheet" href="./dist/toggle-switch.css">
		<link rel="stylesheet" type="text/css" href="./css/estilos.css" media="screen" charset="utf-8" />
		
		<script>
			$('.btn-toggle').click(function() {
				$(this).find('.btn').toggleClass('active');  

				if ($(this).find('.btn-primary').size()>0) {
					$(this).find('.btn').toggleClass('btn-primary');
				}
				if ($(this).find('.btn-danger').size()>0) {
					$(this).find('.btn').toggleClass('btn-danger');
				}
				if ($(this).find('.btn-success').size()>0) {
					$(this).find('.btn').toggleClass('btn-success');
				}
				if ($(this).find('.btn-info').size()>0) {
					$(this).find('.btn').toggleClass('btn-info');
				}
				$(this).find('.btn').toggleClass('btn-default');
			});

			$('form').submit(function(){
			alert($(this["options"]).val());
			return false;
			});
		</script>

	</head>

    <body tyle="position: static>   
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
								<div id="temperaturaDisplay" style="font-size:40px;"></div>
								
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
									<div id="temperaturaDisplayControl" style="font-size:40px;"></div>
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
	<script type="text/javascript">
		function startTime(){
			today=new Date();
			h=today.getHours();
			m=today.getMinutes();
			s=today.getSeconds();
			m=checkTime(m);
			s=checkTime(s);
			temp="15";
			document.getElementById('reloj').innerHTML=h+":"+m+":"+s;
			document.getElementById('temperaturaDisplay').innerHTML=temp+" ºC";
			document.getElementById('temperaturaDisplayControl').innerHTML=temp+" ºC";
			t=setTimeout('startTime()',500);
		}
		function checkTime(i){
			if (i<10) {i="0" + i;}
			return i;
		}
		window.onload=function(){ startTime();	}
	</script>
</html>