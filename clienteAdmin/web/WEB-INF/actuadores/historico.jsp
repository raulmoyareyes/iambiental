<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Menú lateral</button>
        </p>
        <div class="row padding1em">
            <h1>Histórico - ${actuador.descripcion}</h1>

            <div id="graficoHistorico"></div>
            <form class="form-horizontal" role="form">
                <input type="hidden" name="id" value="${actuador.id}"/>
                <div class="form-group">

                    <label for="dtp_input1" class="col-sm-2 control-label">Inicio</label>
                    <div class="input-group date form_datetime col-sm-10" data-date="" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                        <input class="form-control" type="text" value="" readonly placeholder="Desde el origen de los tiempos">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                    </div>
                    <input type="hidden" id="dtp_input1" value="" name="fechaInicio" /><br/>
                </div>


                <div class="form-group">

                    <label for="dtp_input2" class="col-sm-2 control-label">Fin</label>
                    <div class="input-group date form_datetime col-sm-10" data-date="" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input2">
                        <input class="form-control" type="text" value="" readonly placeholder="Hasta el último registro">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                    </div>
                    <input type="hidden" id="dtp_input2" value="" name="fechaFinal" /><br/>
                </div>



                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="filtrar" class="btn btn-primary">Filtrar por fecha</button>
                        <a href="${appUrl}/actuadores/ver?id=${actuador.id}" rol="button" class="btn btn-default">Volver al actuador</a>
                    </div>
                </div>
            </form>
        </div><!--/row-->
    </div><!--/span-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <div class="list-group">
            <a href="${appUrl}/actuadores/insertar" class="list-group-item"><span class="glyphicon glyphicon-plus-sign"></span> Insertar nuevo actuador</a>
            <a href="${appUrl}/actuadores/editar?id=${sensor.id}" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> Editar actuador</a>
            <a href="#" class="list-group-item" onclick="modalEliminarActuador('${actuador.id}', '${actuador.descripcion}')"><span class="glyphicon glyphicon-minus-sign"></span> Eliminar actuador</a>
        </div>
        <div class="list-group">
            <c:forEach var="a" items="${actuadores}" varStatus="estado">
                <a href="${appUrl}/actuadores/ver?id=${a.id}" class="list-group-item ${(a.id == actuador.id)?'active':''}">${a.id} - ${a.descripcion}</a>
            </c:forEach>
        </div>
    </div><!--/span-->
</div><!--/row-->