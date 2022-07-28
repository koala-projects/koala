package cn.koala.office;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelServiceTest {

  private final ExcelService excelService = new EasyExcelService();

  @AllArgsConstructor
  @Data
  @NoArgsConstructor
  public static class Item {
    @ExcelProperty("名称")
    String name;
    @ExcelProperty("内容")
    Integer value;
  }

  @Test
  public void read() throws FileNotFoundException {
    List<Item> data = excelService.read("src/test/resources/read.xlsx", Item.class);
    Assertions.assertEquals(data.size(), 5);
    data = excelService.read(new FileInputStream("src/test/resources/read.xlsx"), Item.class);
    Assertions.assertEquals(data.size(), 5);
  }

  @Test
  public void write() throws FileNotFoundException {
    List<Item> data = data();
    excelService.write("src/test/resources/write.xlsx", data, Item.class);
    File write = new File("src/test/resources/write.xlsx");
    Assertions.assertTrue(write.exists());
    excelService.write(new FileOutputStream("src/test/resources/write.xlsx"), data, Item.class);
    Assertions.assertTrue(write.delete());
  }

  @Test
  public void template() throws FileNotFoundException {
    List<Item> data = data();
    excelService.template("src/test/resources/template.xlsx", "src/test/resources/write.xlsx", data, Item.class);
    File write = new File("src/test/resources/write.xlsx");
    Assertions.assertTrue(write.exists());
    excelService.template("src/test/resources/template.xlsx", new FileOutputStream("src/test/resources/write.xlsx"), data, Item.class);
    Assertions.assertTrue(write.delete());
  }

  protected List<Item> data() {
    List<Item> data = new ArrayList<>(10);
    for (int i = 1; i < 11; i++) {
      data.add(new Item("name-" + i, i));
    }
    return data;
  }
}
