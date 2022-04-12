package cn.houtaroy.koala.component.jdbc;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class InMemoryDatabaseServiceImpl implements DatabaseService {

  protected Map<String, Database> databases = new HashMap<>();

  @Override
  public List<Database> list(String name) {
    return name == null || "".equals(name) ? databases.values().stream().toList()
      : databases.keySet().stream().filter(key -> key.contains(name)).map(key -> databases.get(key)).toList();
  }

  @Override
  public Optional<Database> loadByName(String name) {
    return Optional.ofNullable(databases.get(name));
  }

  @Override
  public Database add(Database database) {
    databases.put(database.getName(), database);
    return database;
  }

  @Override
  public void delete(String name) {
    databases.remove(name);
  }
}
