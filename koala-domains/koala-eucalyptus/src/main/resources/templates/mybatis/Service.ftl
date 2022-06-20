package ${packageName}.services;

import ${packageName}.entities.${domain.code.capitalize}Entity;
import ${packageName}.repositories.${domain.code.capitalize}Repository;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * ${domain.name}服务类
 *
 * @author Koala Eucalyptus
 */
@Component
@Data
@RequiredArgsConstructor
public class ${domain.code.capitalize}Service {
  private final ${domain.code.capitalize}Repository repository;

  public Page<${domain.code.capitalize}Entity> list(Map<String, Object> parameters, Pageable pageable) {
    Assert.notNull(pageable, "分页参数不能为空");
    int pageNumber = pageable.getPageNumber();
    PageHelper.startPage(pageNumber == 0 ? 1 : pageNumber, pageable.getPageSize());
    com.github.pagehelper.Page<${domain.code.capitalize}Entity> result =
      (com.github.pagehelper.Page<${domain.code.capitalize}Entity>) getRepository().findAll(parameters, pageable);
    return new PageImpl<>(result, pageable, result.getTotal());
  }

  public List<${domain.code.capitalize}Entity> list(Map<String, Object> parameters) {
    return getRepository().findAll(parameters, null);
  }
  <#if domain.idProperty??>

  public Optional<${domain.code.capitalize}Entity> load(${domain.idProperty.type} id) {
    return getRepository().findById(id);
  }
  </#if>

  public void add(${domain.code.capitalize}Entity entity) {
    getRepository().add(entity);
  }

  public void update(${domain.code.capitalize}Entity entity) {
    getRepository().update(entity);
  }

  public void delete(${domain.code.capitalize}Entity entity) {
    getRepository().delete(entity);
  }
}
