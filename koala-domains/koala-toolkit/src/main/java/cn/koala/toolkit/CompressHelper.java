package cn.koala.toolkit;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 压缩帮助类
 *
 * @author Houtaroy
 */
public abstract class CompressHelper {
  public static void compress(@NonNull File file, @NonNull File dest, @NonNull String type) throws IOException, ArchiveException {
    Assert.isTrue(file.exists(), "目标文件不存在");
    try (ArchiveOutputStream output = createArchiveOutputStream(dest, type)) {
      compress(output, file, "");
    }
  }

  public static ArchiveOutputStream createArchiveOutputStream(@NonNull File file, @NonNull String type) throws IOException, ArchiveException {
    return ArchiveStreamFactory.DEFAULT.createArchiveOutputStream(type, Files.newOutputStream(file.toPath()));
  }

  public static void compress(@NonNull ArchiveOutputStream output, @NonNull File file, String path) throws IOException {
    Assert.isTrue(file.exists(), "目标文件不存在");
    String filePath = StringUtils.hasLength(path) ? path + file.getName() : file.getName();
    if (file.isDirectory()) {
      File[] children = file.listFiles();
      if (ArrayUtils.isEmpty(children)) {
        ArchiveEntry entry = output.createArchiveEntry(file, filePath);
        output.putArchiveEntry(entry);
        output.closeArchiveEntry();
      } else {
        for (File child : children) {
          compress(output, child, filePath + File.separator);
        }
      }
    } else {
      ArchiveEntry entry = output.createArchiveEntry(file, filePath);
      output.putArchiveEntry(entry);
      byte[] bs = FileUtils.readFileToByteArray(file);
      output.write(bs);
      output.closeArchiveEntry();
    }
  }
}
