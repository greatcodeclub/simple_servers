# EventMachine (http://rubyeventmachine.com/) is an event-driven networking library for Ruby.
#
# Running:
#
#   $ gem install eventmachine
#   $ ruby server.rb
#
require 'eventmachine'

module Connection
  def post_init
    puts "New connection"
  end

  def receive_data(data)
    puts "Received: " + data

    send_data "Bye!\n"

    close_connection_after_writing
  end
end

EventMachine.run do
  EventMachine.start_server 'localhost', 3000, Connection
  puts "Server listening on port 3000"
end