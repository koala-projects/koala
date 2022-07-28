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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

  private final ExcelService excelService = new DefaultExcelWebService(new EasyExcelReader(), new EasyExcelWriter());
  private final List<Item> data = data();

  @Test
  public void read() throws FileNotFoundException {
    File file = new File("src/test/resources/read.xlsx");
    List<Item> data = excelService.getReader().read(file.getPath(), Item.class);
    Assertions.assertEquals(data.size(), 5);
    data = excelService.getReader().read(new FileInputStream(file), Item.class);
    Assertions.assertEquals(data.size(), 5);
  }

  @Test
  public void write() throws FileNotFoundException {
    File file = new File("src/test/resources/temp.xlsx");
    excelService.getWriter().write(file.getPath(), data, Item.class);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
    excelService.getWriter().write(new FileOutputStream(file), data, Item.class);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
    List<List<String>> headers = new ArrayList<>();
    headers.add(List.of("名称"));
    headers.add(List.of("内容"));
    List<LinkedHashMap<String, Object>> mapData = new ArrayList<>(100);
    for (int i = 1; i < 101; i++) {
      mapData.add(new LinkedHashMap<>(Map.of("name", "name-" + i, "value", i)));
    }
    excelService.getWriter().write(file.getPath(), headers, mapData);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
  }


  @Test
  public void template() throws FileNotFoundException {
    File file = new File("src/test/resources/temp.xlsx");
    excelService.getWriter().template("src/test/resources/template.xlsx", file.getPath(), data, Item.class);
    Assertions.assertTrue(file.exists());
    Assertions.assertTrue(file.delete());
    excelService.getWriter().template("src/test/resources/template.xlsx", new FileOutputStream(file), data, Item.class);
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
