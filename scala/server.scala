import java.net._
import java.io._
import scala.io._

val server = new ServerSocket(3000)
println("Server listening on port 3000")

while (true) {
    val s = server.accept()
    println("New connection")
    new Thread(new Runnable {
      def run() {
        val in = new BufferedSource(s.getInputStream()).getLines()
        val out = new PrintStream(s.getOutputStream())
        println(in.next())
        out.println("Bye!\n")
        out.flush()
        s.close()
      }
    }).start()
}
