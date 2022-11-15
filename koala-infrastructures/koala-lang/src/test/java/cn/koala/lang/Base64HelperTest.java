package cn.koala.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * Base64帮助类测试类
 *
 * @author Houtaroy
 */
public class Base64HelperTest {
  @Test
  public void test() throws IOException {
    File file = new File("src/test/resources/chitanda.jpg");
    File newFile = new File("src/test/resources/chitanda-2.jpg");
    String base64 = Base64Helper.encode(file);
    Assertions.assertNotNull(base64);
    String base64WithDescription = Base64Helper.encode(file, true);
    Assertions.assertTrue(base64WithDescription.startsWith("data:image/jpeg;base64,"));
    Assertions.assertDoesNotThrow(() -> Base64Helper.decode(base64, newFile));
    Assertions.assertDoesNotThrow(() -> Base64Helper.decode(base64WithDescription, newFile));
    Assertions.assertTrue(newFile.delete());
  }
}
