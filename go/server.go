package main

import (
  "net"
  "fmt"
  "os"
)

func main() {
  listener, err := net.Listen("tcp", ":3000")
  checkError(err)

  for {
    conn, err := listener.Accept()
    checkError(err)
    fmt.Println("New connection")

    go handleConnection(conn)
  }
}

func handleConnection(conn net.Conn) {
  defer conn.Close()

  var data [512]byte
  size, err := conn.Read(data[0:])
  if err != nil {
    return
  }

  fmt.Println("Received data", string(data[0:size]))

  conn.Write([]byte("Bye!\n"))
}

func checkError(err error) {
  if err != nil {
  	fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
  	os.Exit(1)
  }
}
