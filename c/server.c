// A simple C server.
//
// Running:
//
//   $ cc server.c
//   $ ./a.out
//
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char *argv[]) {
  int listen_fd = socket(AF_INET, SOCK_STREAM, 0);
  
  struct sockaddr_in serv_addr;
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  serv_addr.sin_port = htons(3000);

  bind(listen_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr));

  listen(listen_fd, 10);

  puts("Server listening on port 3000");

  while(1) {
    int connection_fd = accept(listen_fd, (struct sockaddr*)NULL, NULL);
    puts("New connection");

    char buf[1024] = "";
    read(connection_fd, &buf, 1024);
    printf("Received: %s\n", buf);

    write(connection_fd, "Bye!\n", 5);

    close(connection_fd);
  }
}