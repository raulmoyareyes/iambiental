<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <hr/>
        <footer>
            <p>Proyecto de prácticas de la asignatura <em>Inteligencia Ambiental</em> de <em>4º de Grado en Ingeniería Informática</em> realizado por Gabriel Fernández Moral, Raúl Moya Reyes, Vicente Plaza Franco y Agustín Ruiz Linares</p>
        </footer>

    </div><!--/.container-->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>-->
    
    <script src="${appUrl}/js/raphael-min.js"></script>
    <script src="${appUrl}/js/jquery-1.11.0.min.js"></script>
    <script src="${appUrl}/js/morris-0.4.1.min.js"></script>
    <script src="${appUrl}/js/bootstrap.min.js"></script>
    <script src="${appUrl}/js/offcanvas.js"></script>
    <script src="${appUrl}/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${appUrl}/js/bootstrap-datetimepicker.es.js"></script>
    <script src="${appUrl}/js/script.js"></script>
                
    <script>
        
        var day_data = [
            <c:forEach var="h" items="${historico}" varStatus="estado">
                {"period": ${h.fecha}, "dato": ${h.dato}, "estado": ${h.estado}},
            </c:forEach>
        ];
        Morris.Line({
        element: 'graficoHistorico',
        data: day_data,
        xkey: 'period',
        ykeys: ['dato', 'estado'],
        labels: ['Dato', 'Estado']
        });
        
        <c:if test="${empty historico}">
            $("#graficoHistorico").text("No hay resultados.");
            $("#graficoHistorico").addClass('alert alert-info');
        </c:if>
    </script>
    
    <script type="text/javascript">
        $('.form_datetime').datetimepicker({
            language:  'es',
            weekStart: 1,
            todayBtn:  1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    forceParse: 0,
            showMeridian: 1
        });
    </script>
    
    
    
    
    
</body>
</html>
