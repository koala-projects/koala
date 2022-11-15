package cn.koala.lang.zip;

import org.apache.commons.lang3.StringUtils;

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
      zipFiles(files.toArray(new File[0]), "", zos);
    }
  }

  /**
   * 压缩目录
   *
   * @param dir  目录文件
   * @param path 上级目录
   * @param zos  压缩输出流
   * @throws IOException IO异常
   */
  public static void zipDir(File dir, String path, ZipOutputStream zos) throws IOException {
    zipFiles(dir.listFiles(), StringUtils.isBlank(path) ? dir.getName() : path + File.separator + dir.getName(), zos);
  }

  /**
   * 压缩文件列表
   *
   * @param files 文件列表
   * @param path  上级目录
   * @param zos   压缩输出流
   * @throws IOException IO异常
   */
  public static void zipFiles(File[] files, String path, ZipOutputStream zos) throws IOException {
    if (files == null || files.length == 0) {
      return;
    }
    for (File file : files) {
      if (file.isDirectory()) {
        zipDir(file, path, zos);
      } else {
        zipFile(file, path, zos);
      }
    }
  }

  /**
   * 压缩文件
   *
   * @param file 文件
   * @param path 上级目录
   * @param zos  压缩输出流
   * @throws IOException IO异常
   */
  public static void zipFile(File file, String path, ZipOutputStream zos) throws IOException {
    zos.putNextEntry(new ZipEntry(path + File.separator + file.getName()));
    zos.write(Files.readAllBytes(file.toPath()));
    zos.closeEntry();
  }
}
