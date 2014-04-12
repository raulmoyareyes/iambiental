
$(function() {
    $('.btn-toggle').click(function() {
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

    $('form').submit(function() {
        alert($(this["options"]).val());
        return false;
    });

    setInterval(function(){startTime();}, 500);
});

$(function startTime() {
    console.log("h");
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
});
