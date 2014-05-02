
$(function() {
    /* Spinner */
    $("input[name='spinner']").TouchSpin({min: 0, max: 40, step: 0.5, decimals: 1,
        boostat: 5, maxboostedstep: 10, postfix: '\u00BAC'});
    $(".bootstrap-touchspin-up").click(function() {
        $.ajax({
            type: "POST",
            url: "http://localhost:8084/clienteUso/c/actuador?id=" +
                    $(".spinner").attr('id') + "&dato=" + $(".spinner").val()
        });

    });
    $(".bootstrap-touchspin-down").click(function() {
        $.ajax({
            type: "POST",
            url: "http://localhost:8084/clienteUso/c/actuador?id=" +
                    $(".spinner").attr('id') + "&dato=" + $(".spinner").val()
        });

    });

    /* Swipe */
    $(document).on("swipeleft", function() {
        $('.carousel').carousel('next');
    });
    $(document).on("swiperight", function() {
        $('.carousel').carousel('prev');
    });

    /* Redimensionar objetos */
    responsive();
    $(window).resize(function() {
        responsive();
    });

    /* Toggle Button */
    $('.toggle-button').click(function() {
        if ($(this).children().attr('class') === 'on') {
            $(this).children().attr('class', "off");
            $(this).children().html("OFF");
            $.ajax({
                type: "POST",
                url: "http://localhost:8084/clienteUso/c/actuador?id="
                        + $(this).parent().attr('id') + "&estado=0"
            });
        } else {
            $(this).children().attr('class', "on");
            $(this).children().html("ON");
            $.ajax({
                type: "POST",
                url: "http://localhost:8084/clienteUso/c/actuador?id="
                        + $(this).parent().attr('id') + "&estado=1"
            });
        }
    });

    /* Seleccionar dependencia */
    var dependencia = 1; //$(" ").val());
    $(".dropdown-menu li a").click(function() {
        $("input[name='dependencia']").val($(this).html());
        recargarDatos(dependencia);//$(" ").val());
    });

    startTime();
    setInterval(function() {
        startTime();
    }, 500);
    setInterval(function() {
        recargarDatos(dependencia);
    }, 10000);

    /* Salta reloj cuando esta sin uso */
    var sinUso = setInterval(function() {
        $('.carousel').carousel(0);
    }, 15000);
    $(document).click(function() {
        clearInterval(sinUso);
        sinUso = setInterval(function() {
            $('.carousel').carousel(0);
        }, 15000);
    });
});

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    var rm = (m < 10) ? m = "0" + m : m;
    var s = (s < 10) ? s = "0" + s : s;
    $('#reloj').html(h + ":" + m + ":" + s);
}

function responsive() {
    $('.carousel-inner').height(window.innerHeight - (window.innerHeight * 0.21));
    $('.carousel-inner').css('font-size', window.innerWidth * 0.05);
    $('#reloj').css('font-size', window.innerWidth * 0.15);
    $('#temperaturaControl').height(window.innerHeight - (window.innerHeight * 0.21));
    $('#lamparasControl').height(window.innerHeight - (window.innerHeight * 0.21));
}

function recargarDatos(dependencia) {
    // hacer peticiones al controlador para coger datos.

    $.getJSON("http://localhost:8084/servidorWeb/recursos/sensores/dependencia/" + dependencia, function(data) {
        for (var i = 0, len = data.length; i < len; i++) {
            console.log(data[i]);
        }
    });

}
