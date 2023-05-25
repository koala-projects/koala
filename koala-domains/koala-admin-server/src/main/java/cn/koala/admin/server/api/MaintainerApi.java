package cn.koala.admin.server.api;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.repository.MaintainerRepository;
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
 * 运维工程师管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/maintainers")
@RequiredArgsConstructor
public class MaintainerApi {

  private final MaintainerRepository repository;

  @GetMapping
  public Flux<Maintainer> list() {
    return repository.findAll();
  }

  @GetMapping("{id}")
  public Mono<Maintainer> load(@PathVariable("id") String id) {
    return repository.findById(id);
  }

  @PostMapping
  public Mono<Maintainer> create(@RequestBody Maintainer application) {
    application.setId(UUID.randomUUID().toString());
    return repository.save(application);
  }

  @PutMapping("{id}")
  public Mono<Maintainer> update(@PathVariable("id") String id, @RequestBody Maintainer application) {
    application.setId(id);
    return repository.save(application);
  }

  @DeleteMapping("{id}")
  public Mono<Void> delete(@PathVariable("id") String id) {
    return repository.deleteById(id);
  }
}
