package UserHandler;

import Server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class UserHandler extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(UserHandler.class.getName());
    private final Server server;
    private final BufferedReader in;
    private final PrintWriter out;
    private final Socket socket;
    private static final String date = "[" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + "]";

    public UserHandler(Server server, Socket socket) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
        start();

    }
    public void sendMsg(String msg) {
            out.println(date + msg);
            out.flush();
    }
    @Override
    public void run() {
        String newUser;
        try {
            newUser = in.readLine();
            server.sendMessageToEveryone(newUser + " is connected!");
            logger.info("{} {} {} is connected!", date, newUser, socket);
            System.out.println("Echo: " + newUser + " is connected!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                String msg = in.readLine();
                if (msg == null) {
                    server.sendMessageToEveryone(newUser + " disconnected ");
                    System.out.println("Echo: " + newUser + " disconnected!");
                    logger.info("{} {}, {} disconnected", date, newUser, socket);
                    socket.close();
                    break;
                } else {
                    logger.info("{} New message accepted: {}", date, msg);
                    System.out.println("Echo: " + msg);
                    server.sendMessageToEveryone(msg);
                }
            } catch (IOException ignored) {
            }
        }
    }
}
