package clsoft.springFramework.spring5WebApp.repositories;

import clsoft.springFramework.spring5WebApp.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
