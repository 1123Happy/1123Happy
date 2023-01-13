var host="http://localhost";
var port=8080;
$( "#getTradeAccumulation" ).click(function() {
  $.ajax({
    method: "GET",
    url: host+":"+port+"/gettr"+"/getTradeA",
     beforeSend: function(request) {
                request.setRequestHeader("Access-Control-Allow-Origin","*");
              },
    data: { time: $("#t-div").val().trim(), tradeType: "BUY" },
    success:function(msg){
    $("#t-buy").val(msg);
    }
  })
//    .done(function( msg ) {
//      $("#t-buy").val(msg);
//    });
    $.ajax({
        method: "GET",
        url: host+":"+port+"/gettr"+"/getTradeA",
         beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin","*");
          },
        data: { time: $("#t-div").val().trim(), tradeType: "SELL" }
      })
        .done(function( msg ) {
         $("#t-sell").val(msg);
        });
});