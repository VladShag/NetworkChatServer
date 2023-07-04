import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static final ArrayList<UserHandler> userList = new ArrayList<>();
    private static final int PORT = 8080;

    public Server() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started!");
            try {
                while (true) {
                    clientSocket = server.accept();
                    System.out.println("New client accepted!");
                    UserHandler user = new UserHandler(this, clientSocket);
                    userList.add(user);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessageToEveryone(String msg) {
        for (UserHandler user : userList) {
            user.sendMsg(msg);
        }
    }

}
