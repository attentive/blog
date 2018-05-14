(function () {
  console.log("Setting up hot reload ..."); 
  var socket = new WebSocket("ws://localhost:59045");
  socket.onmessage = event => console.log(event.data);
})();
