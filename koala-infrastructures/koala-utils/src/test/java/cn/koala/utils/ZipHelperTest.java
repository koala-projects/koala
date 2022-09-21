package cn.koala.utils;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.ZipFile;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public class ZipHelperTest {
  @Test
  public void zipDir() throws IOException {
    File dir = new File("src/test/resources/srcDir");
    File[] files = dir.listFiles();
    Assertions.assertNotNull(files);
    ZipHelper.zip(Arrays.asList(files), new FileOutputStream("src/test/resources/dest.zip"));
    try (ZipFile zip = new ZipFile("src/test/resources/dest.zip")) {
      InputStream is = zip.getInputStream(zip.getEntry("file.txt"));
      String content = IOUtils.readLines(is, StandardCharsets.UTF_8).get(0);
      Assertions.assertEquals(content, "压缩文件");
    }
    Assertions.assertTrue(new File("src/test/resources/dest.zip").delete());
  }
}
