        <hr/>
        <footer>
            <p>Proyecto de prácticas de la asignatura <em>Inteligencia Ambiental</em> de <em>4º de Grado en Ingeniería Informática</em> realizado por Gabriel Fernández Moral, Raúl Moya Reyes, Vicente Plaza Franco y Agustín Ruiz Linares</p>
        </footer>

    </div><!--/.container-->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>-->
    
    <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="${appUrl}/js/jquery-1.11.0.min.js"></script>
    <script src="http://cdn.oesmith.co.uk/morris-0.4.1.min.js"></script>
    <script src="${appUrl}/js/bootstrap.min.js"></script>
    <script src="${appUrl}/js/offcanvas.js"></script>
    <script src="${appUrl}/js/script.js"></script>
    
    
    <script>
        var day_data = [
            {"period": "2012-10-01 12:00", "temperatura": 1, "sorned": 11},
            {"period": "2012-10-01 12:01", "temperatura": 2, "sorned": 11},
            {"period": "2012-10-01 12:02", "temperatura": 3, "sorned": 11},
            {"period": "2012-10-01 12:03", "temperatura": 4, "sorned": 11},
            
            {"period": "2012-10-01 12:05", "temperatura": 4, "sorned": 11},
            {"period": "2012-10-01 12:06", "temperatura": 5, "sorned": 11},
            {"period": "2012-10-01 12:07", "temperatura": 4, "sorned": 11},
            {"period": "2012-10-01 12:08", "temperatura": 6, "sorned": 11},
            {"period": "2012-10-01 12:09", "temperatura": 7, "sorned": 11},
            {"period": "2012-10-01 12:10", "temperatura": 8, "sorned": 11},
            {"period": "2012-10-01 12:30", "temperatura": 4, "sorned": 10}
        ];
        Morris.Line({
        element: 'graficoHistorico',
        data: day_data,
        xkey: 'period',
        ykeys: ['temperatura'],
        labels: ['Temperatura']
        });

        //# sourceURL=jsbin.otaxef.115-5.js
        //@ sourceURL=jsbin.otaxef.115-5.js
    </script>
</body>
</html>
