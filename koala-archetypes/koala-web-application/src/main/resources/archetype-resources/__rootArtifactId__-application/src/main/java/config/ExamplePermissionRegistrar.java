#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import cn.koala.system.permission.CrudPermissionRegistrar;
import org.springframework.stereotype.Component;

/**
 * TODO: 请修改排序索引, 建议业务功能从30000开始, 30000以下为系统保留权限
 * #(description)权限注册器
 *
 * @author Koala Web Application
 */
@Component
public class ExamplePermissionRegistrar extends CrudPermissionRegistrar {

  public ExamplePermissionRegistrar(){
    super("example","示例管理", 30000, null);
  }
  
}
