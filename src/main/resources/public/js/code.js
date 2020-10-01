var app = (function(){
    var targetUrl = window.location.href;
    var cleanTable = function(){
        $("#mainTable").find("td[name='info']").each(function(){
            $(this).parents("tr").remove();
        });
    };
    var callback = function(data){
        cleanTable();
        data = data.slice(0,-1);
        data = data.substring(1);
        data = $.parseJSON("[" + data + "]");
        data.map(function(code){
            var str = "<tr>"+
              "<td name='info'>"+code.content+"</td>"+
              "<td>"+code.date+"</td>"+
            "</tr>";
            $("#mainTable").append(str);
        });
    };
    return {
        registryCode: function() {
            var code = $("#code").val();
            if(code !== undefined && code !==""){
                const Http = new XMLHttpRequest();
                const url=targetUrl+"codes";
                Http.open("POST", url);
                Http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                Http.send("code="+code);
                Http.onreadystatechange = function(){
                    if(this.readyState===4 && this.status===200){
                        var response = Http.responseText;
                        callback(response);
                    }
                }
            }
        }
    }
})();