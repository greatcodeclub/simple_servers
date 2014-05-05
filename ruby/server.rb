# A simple Ruby thread-based server.
#
# Running:
#
#   $ ruby server.rb
#
require 'socket'
require 'thread'

server = TCPServer.new(3000)
puts "Server listening on port 3000"

loop do
  connection = server.accept
  puts "New connection"
  
  Thread.new do
    data = connection.read
    puts "Received: " + data

    connection.write "Bye!\n"

    connection.close
  end
end