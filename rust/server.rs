// A simple Rust (rust-lang.org) server.
//
// Running:
//
//   $ rustc server.rs
//   $ ./server
//
// Tested under Rust 0.10
// Based on https://github.com/mozilla/rust/issues/9626
//      and https://gist.github.com/andelf/8668088

use std::io::net::tcp::TcpListener;
use std::io::net::ip::{Ipv4Addr, SocketAddr};
use std::io::{Acceptor, Listener};
use std::io::BufferedStream;
use std::io::println;

fn main() {
  let addr = SocketAddr { ip: Ipv4Addr(127, 0, 0, 1), port: 3000 };
  let listener = TcpListener::bind(addr);

  // bind the listener to the specified address
  let mut acceptor = listener.listen();
  println("Listening on port 3000");

  // accept connections and process them
  for stream in acceptor.incoming() {
      spawn(proc() {
          println("New connection");
          
          let mut stream = BufferedStream::new(stream);
          let reads = stream.read_line().unwrap();
          println(format!("Received: {}", reads));

          stream.write_str("Bye!\n").ok();
          stream.flush().ok();

          drop(stream);
      });
  }

  // close the socket server
  drop(acceptor);
}
