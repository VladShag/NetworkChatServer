package ServerTest;

import Server.Server;
import UserHandler.UserHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
//    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    @BeforeAll
//    public static void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//    }
//
//
//    @Test
//    public void checkConnectionTest() throws Exception {
//        Server server = Mockito.mock(Server.class);
//        Assertions.assertEquals(outContent.toString(),"Server started!");
//
//    }
//    @AfterAll
//    public static void restoreStreams() {
//        System.setOut(null);
//    }

}
