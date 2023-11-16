package cn.koala.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 压缩工具类
 *
 * @author Houtaroy
 */
public abstract class CompressUtils {

  public static void compress(@NonNull File source, @NonNull File dest, @NonNull String archiverName)
    throws IOException, ArchiveException {

    Assert.notNull(source, "源文件不能为空");
    Assert.notNull(dest, "目标文件不能为空");
    Assert.hasLength(archiverName, "压缩类型不能为空");
    Assert.isTrue(source.exists(), "目标文件不存在");
    try (ArchiveOutputStream output = createArchiveOutputStream(dest, archiverName)) {
      compress(output, source, "");
    }
  }

  private static ArchiveOutputStream createArchiveOutputStream(File file, String archiverName)
    throws IOException, ArchiveException {

    return ArchiveStreamFactory.DEFAULT.createArchiveOutputStream(archiverName, Files.newOutputStream(file.toPath()));
  }

  private static void compress(ArchiveOutputStream output, File source, String path) throws IOException {
    String filePath = StringUtils.hasLength(path) ? path + source.getName() : source.getName();
    if (source.isDirectory()) {
      File[] children = source.listFiles();
      if (ObjectUtils.isEmpty(children)) {
        ArchiveEntry entry = output.createArchiveEntry(source, filePath);
        output.putArchiveEntry(entry);
        output.closeArchiveEntry();
      } else {
        for (File child : children) {
          compress(output, child, filePath + File.separator);
        }
      }
    } else {
      ArchiveEntry entry = output.createArchiveEntry(source, filePath);
      output.putArchiveEntry(entry);
      byte[] bs = FileUtils.readFileToByteArray(source);
      output.write(bs);
      output.closeArchiveEntry();
    }
  }
}
