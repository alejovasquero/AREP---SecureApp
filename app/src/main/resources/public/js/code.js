var app = (function(){
    var targetUrl = window.location.href;
    return {
        result: function() {
            var code = $("#texto").val();
            console.log(code)
            $.ajax({
                url: targetUrl + '/result',
                type: "POST",
                data: code,
                contentType: 'application/json; charset=utf-8',
                success: function (data, status, xhr) {
                    $("#result").text("PROMEDIO :"  +  (data.result!=undefined? data.result : "PLEASE LOGIN"));
                },
                error: function (jqXhr, textStatus, errorMessage) {
                    $("#result").text("ERROR");
                }
            });
        }
    }
})();