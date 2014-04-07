<!DOCTYPE html>
<html lang="en">
  <head>
        <title>Cliente Táctil</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap-transition.js"></script>
    </head>
	<style>
		body { height: 100%; width: 100%; position: absolute; }
		
		#main    { position:relative; background:red; height:500px; width:100%; }
		#leftcol { position:absolute; background:blue; bottom:20px; text-align:right; }
		#temperatura { position:absolute; left:10%; background:grey; top:-30%;  height:100%;   }
		#lamparas { position:absolute; right:10%; background:grey; top:-30%;  height:100%;   }
		
	</style>
    <body tyle="position: static>   
		<div class="row">
			<div align="center" class="col-md-9" role="main">
				<div id="carousel-example-generic" data-interval="false" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol visibility="hide" class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<div class="item active">
							<img data-src="holder.js/900x500/auto/#666:#666" alt="900x500" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI5MDAiIGhlaWdodD0iNTAwIj48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzY2NiI+PC9yZWN0Pjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjQ1MCIgeT0iMjUwIiBzdHlsZT0iZmlsbDojNjY2O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjU2cHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+OTAweDUwMDwvdGV4dD48L3N2Zz4="/>
							<div position="relative" align="center" class="carousel-caption">
								<h1>ESTA HORA</h1>
								<h1>ESTA TEMP</h1>
								
								</br></br></br></br></br></br>
							</div>
						</div>
						<div class="item">
							<img data-src="holder.js/900x500/auto/#666:#666" alt="900x500" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI5MDAiIGhlaWdodD0iNTAwIj48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzY2NiI+PC9yZWN0Pjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjQ1MCIgeT0iMjUwIiBzdHlsZT0iZmlsbDojNjY2O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjU2cHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+OTAweDUwMDwvdGV4dD48L3N2Zz4="/>
							<div class="carousel-caption">
								
								<div id="temperatura" class="span8">
									<p>TEMPERATURA</p>
								</div>
								
								<div id="lamparas" class="span8">
									<p>LAMPARAS</p>
								</div>
								</br></br></br></br></br></br>
								<h1>Bontoncicos y displays</h1>
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