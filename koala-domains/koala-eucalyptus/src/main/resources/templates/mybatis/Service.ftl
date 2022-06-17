package ${packageName}.services;

import ${packageName}.entities.${domain.className}Entity;
import ${packageName}.repositories.${domain.className}Repository;

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
public class ${domain.className}Service {
  private final ${domain.className}Repository repository;

  public Page<${domain.className}Entity> list(Map<String, Object> parameters, Pageable pageable) {
    Assert.notNull(pageable, "分页参数不能为空");
    int pageNumber = pageable.getPageNumber();
    PageHelper.startPage(pageNumber == 0 ? 1 : pageNumber, pageable.getPageSize());
    com.github.pagehelper.Page<${domain.className}Entity> result =
      (com.github.pagehelper.Page<${domain.className}Entity>) getRepository().findAll(parameters, pageable);
    return new PageImpl<>(result, pageable, result.getTotal());
  }

  public List<${domain.className}Entity> list(Map<String, Object> parameters) {
    return getRepository().findAll(parameters, null);
  }
  <#if domain.idProperty??>

  public Optional<${domain.className}Entity> load(${domain.idProperty.type} id) {
    return getRepository().findById(id);
  }
  </#if>

  public void add(${domain.className}Entity entity) {
    getRepository().add(entity);
  }

  public void update(${domain.className}Entity entity) {
    getRepository().update(entity);
  }

  public void delete(${domain.className}Entity entity) {
    getRepository().delete(entity);
  }
}
