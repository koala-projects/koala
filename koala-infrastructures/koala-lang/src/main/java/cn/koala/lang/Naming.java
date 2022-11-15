package cn.koala.lang;

import com.google.common.base.CaseFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 命名格式类
 * <p>
 * 包含驼峰/帕斯卡/下划线/烤肉的单复数格式
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Naming {
  private Word camel;
  private Word pascal;
  private Word underscore;
  private Word kebab;

  /**
   * 根据驼峰生成命名
   *
   * @param camel 驼峰
   * @return 命名对象
   */
  public static Naming fromCamel(String camel) {
    return new Naming(
      Word.fromSingular(camel),
      Word.fromSingular(StringUtils.capitalize(camel)),
      Word.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camel)),
      Word.fromSingular(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, camel))
    );
  }

  /**
   * 根据帕斯卡生成命名
   *
   * @param pascal 帕斯卡
   * @return 命名对象
   */
  public static Naming fromPascal(String pascal) {
    return fromCamel(StringUtils.uncapitalize(pascal));
  }

  /**
   * 根据下划线生成命名
   *
   * @param underscore 下划线
   * @return 命名对象
   */
  public static Naming fromUnderScore(String underscore) {
    return fromCamel(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, underscore));
  }

  /**
   * 根据烤肉生成命名
   *
   * @param kebab 烤肉
   * @return 命名对象
   */
  public static Naming fromKebab(String kebab) {
    return fromCamel(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, kebab));
  }
}
