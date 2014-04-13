
$(function() {
    $('.btn-toggle').on("click", function() {
        $(this).find('.btn').toggleClass('active');

        if ($(this).find('.btn-primary').size() > 0) {
            $(this).find('.btn').toggleClass('btn-primary');
        }
        if ($(this).find('.btn-danger').size() > 0) {
            $(this).find('.btn').toggleClass('btn-danger');
        }
        if ($(this).find('.btn-success').size() > 0) {
            $(this).find('.btn').toggleClass('btn-success');
        }
        if ($(this).find('.btn-info').size() > 0) {
            $(this).find('.btn').toggleClass('btn-info');
        }
        $(this).find('.btn').toggleClass('btn-default');
    });


    $("input[name='spinner']").TouchSpin({min: 0,max: 40,step: 0.5,decimals: 1,
        boostat: 5,maxboostedstep: 10,postfix: '\u00BAC'});

    $(document).on("swipeleft", function() {
        $('.carousel').carousel('next');
    });
    $(document).on("swiperight", function() {
        $('.carousel').carousel('prev');
    });

    $('.carousel-inner').height(window.innerHeight);
    $('.carousel-inner').css('font-size',window.innerWidth*0.05);
    $('#reloj').css('font-size',window.innerWidth*0.15);
    $(window).resize(function() {
        $('.carousel-inner').height(window.innerHeight);
        $('.carousel-inner').css('font-size',window.innerWidth*0.05);
        $('#reloj').css('font-size',window.innerWidth*0.15);
    });

    startTime();
    setInterval(function() {
        startTime();
    }, 500);
});

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    var rm = (m < 10) ? m = "0" + m : m;
    var s = (s < 10) ? s = "0" + s : s;
    var temp = "15";
    $('#reloj').html(h + ":" + m + ":" + s);
    $('#temperaturaDisplay').html(temp);
    $('#temperaturaDisplayControl').html(temp);
}
