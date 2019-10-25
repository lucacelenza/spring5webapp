package clsoft.springFramework.spring5WebApp.repositories;

import clsoft.springFramework.spring5WebApp.models.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
