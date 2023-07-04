import java.io.*;
import java.net.Socket;


public class UserHandler extends Thread{
    private final Server server;
    private final BufferedReader in;
    private final PrintWriter out;
    private final Socket socket;
    public UserHandler(Server server, Socket socket) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
        start();

    }
    public void sendMsg(String msg) {
            out.println(msg);
            out.flush();
    }
    @Override
    public void run() {
        while (true) {
            String msg;
            try {
                msg = in.readLine();
                if (msg.equals("exit")) {
                    break;
                } else {
                    System.out.println("Echo: " + msg);
                    server.sendMessageToEveryone(msg);
                }
            } catch (IOException ignored) {}
        }
    }
}
