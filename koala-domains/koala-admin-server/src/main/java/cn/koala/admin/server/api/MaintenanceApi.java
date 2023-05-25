package cn.koala.admin.server.api;

import cn.koala.admin.server.Maintenance;
import cn.koala.admin.server.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 运维关系管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceApi {

  private final MaintenanceRepository repository;

  @GetMapping
  public Flux<Maintenance> list() {
    return repository.findAll();
  }

  @GetMapping("{id}")
  public Mono<Maintenance> load(@PathVariable("id") String id) {
    return repository.findById(id);
  }

  @PostMapping
  public Mono<Maintenance> create(@RequestBody Maintenance application) {
    application.setId(UUID.randomUUID().toString());
    return repository.save(application);
  }

  @PutMapping("{id}")
  public Mono<Maintenance> update(@PathVariable("id") String id, @RequestBody Maintenance application) {
    application.setId(id);
    return repository.save(application);
  }

  @DeleteMapping("{id}")
  public Mono<Void> delete(@PathVariable("id") String id) {
    return repository.deleteById(id);
  }
}
