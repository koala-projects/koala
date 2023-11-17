package #(package).config;

import cn.koala.system.permission.CrudPermissionRegistrar;
import org.springframework.stereotype.Component;

/**
 * TODO: 请修改排序索引, 建议业务功能从30000开始, 30000以下为系统保留权限
 * #(description)权限注册器
 *
 * @author Koala Code Generator
 */
@Component
public class #(name.pascal.singular)PermissionRegistrar extends CrudPermissionRegistrar {

  public #(name.pascal.singular)PermissionRegistrar(){
    super("#(name.kebab.singular)","#(description)管理",30000,null);
  }
  
}
