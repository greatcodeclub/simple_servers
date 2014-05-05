// A simple Node.js server.
//
// Running:
//
//   $ node server.js
//
var net = require('net')

var server = net.createServer(function(connection) {
  console.log('New connection')

  connection.on('data', function(data) {
    console.log('Received', data.toString())

    connection.write('Bye!\n')

    connection.end()
  })
})

server.listen(3000, function() {
  console.log('Server listening on port 3000')
})