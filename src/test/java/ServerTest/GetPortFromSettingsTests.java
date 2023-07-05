package ServerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Server.Server;


public class GetPortFromSettingsTests {


    @Test
    public void wrongNameInPortTest() {
        Assertions.assertThrows(NumberFormatException.class,()-> Server.getPortFromSettings("src/test/testresources/badportname.txt"));
    }
    @Test
    public void noColonInFileTest() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,()-> Server.getPortFromSettings("src/test/testresources/nocoloninfile.txt"));
    }
    @Test
    public void noFileTest() {
        Assertions.assertThrows(RuntimeException.class,()-> Server.getPortFromSettings("src/test/testresources/unexistedfile.txt"));
    }

}
