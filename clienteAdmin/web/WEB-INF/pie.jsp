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
    
    
    <script>/*
     * Play with this code and it'll update in the panel opposite.
     *
     * Why not try some of the options above?
     */
        Morris.Area({
            element: 'area-example',
            data: [
                {y: '2006', a: 100, b: 90},
                {y: '2007', a: 75, b: 65},
                {y: '2008', a: 50, b: 40},
                {y: '2009', a: 75, b: 65},
                {y: '2010', a: 50, b: 40},
                {y: '2011', a: 75, b: 65},
                {y: '2012', a: 100, b: 90}
            ],
            xkey: 'y',
            ykeys: ['a', 'b'],
            labels: ['Series A', 'Series B']
        });

        //# sourceURL=jsbin.otaxef.115-5.js
        //@ sourceURL=jsbin.otaxef.115-5.js
    </script>
</body>
</html>
