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



$( "#getOrderAccumulation" ).click(function() {
  $.ajax({
    method: "GET",
    url: host+":"+port+"/gettr"+"/getOrderA",
     beforeSend: function(request) {
                request.setRequestHeader("Access-Control-Allow-Origin","*");
              },
    data: { p: $("#o-dif").val().trim(), orderType: "BUY" },
    success:function(msg){
    $("#o-buy").val(msg);
    }
  })
    $.ajax({
        method: "GET",
        url: host+":"+port+"/gettr"+"/getOrderA",
         beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin","*");
          },
        data: { p: $("#o-dif").val().trim(), orderType: "SELL" }
      })
        .done(function( msg ) {
         $("#o-sell").val(msg);
        });
});