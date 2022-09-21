package cn.koala.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩帮助类
 *
 * @author Houtaroy
 */
public abstract class ZipHelper {

  /**
   * 压缩文件到指定输出流
   *
   * @param files 文件列表
   * @param os    输出流
   * @throws IOException IO异常
   */
  public static void zip(List<File> files, OutputStream os) throws IOException {
    try (ZipOutputStream zos = new ZipOutputStream(os)) {
      for (File file : files) {
        zos.putNextEntry(new ZipEntry(file.getName()));
        zos.write(Files.readAllBytes(file.toPath()));
        zos.closeEntry();
      }
    }
  }

  /**
   * 压缩文件到HttpServletResponse, 浏览器会直接下载
   *
   * @param files    文件列表
   * @param filename 下载文件名
   * @param response HttpServletResponse
   * @throws IOException IO异常
   */
  public static void zip(List<File> files, String filename, HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment;filename=%s.zip".formatted(filename));
    zip(files, response.getOutputStream());
  }
}
