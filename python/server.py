import socket

HOST = ''
PORT = 3000

socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.bind((HOST, PORT))
socket.listen(1)

while 1:
  connection, address = socket.accept()
  print 'New connection'

  data = connection.recv(1024)
  print 'Received', data

  connection.send('Bye!\n')

  connection.close()
