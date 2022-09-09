package cn.koala.office;

import com.alibaba.excel.EasyExcel;
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

  @AllArgsConstructor
  @Data
  @NoArgsConstructor
  public static class Item {
    @ExcelProperty("名称")
    String name;
    @ExcelProperty("内容")
    Integer value;
  }

  private final List<Item> data = data();

  @Test
  public void read() throws FileNotFoundException {
    File file = new File("src/test/resources/read.xlsx");
    List<Item> data = EasyExcel.read(file.getPath(), Item.class, null).sheet().doReadSync();
    Assertions.assertEquals(data.size(), 5);
    data = EasyExcel.read(new FileInputStream(file), Item.class, null).sheet().doReadSync();
    Assertions.assertEquals(data.size(), 5);
  }

  @Test
  public void write() throws FileNotFoundException {
    File file = new File("src/test/resources/temp.xlsx");
    EasyExcel.write(file.getPath(), Item.class).sheet().doWrite(data);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
    EasyExcel.write(new FileOutputStream(file), Item.class).sheet().doWrite(data);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
  }


  @Test
  public void template() throws FileNotFoundException {
    String templateName = "src/test/resources/template.xlsx";
    File file = new File("src/test/resources/temp.xlsx");
    EasyExcel.write(file.getPath(), Item.class).withTemplate(templateName).sheet().doFill(data);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
    EasyExcel.write(new FileOutputStream(file), Item.class).withTemplate(templateName).sheet().doFill(data);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
  }

  protected List<Item> data() {
    List<Item> data = new ArrayList<>(10);
    for (int i = 1; i < 11; i++) {
      data.add(new Item("name-" + i, i));
    }
    return data;
  }
}
