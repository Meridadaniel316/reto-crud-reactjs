package co.com.sofka.crud.repositories;

import co.com.sofka.crud.components.Items;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Items, Long> {
}
