package co.com.sofka.crud.repositories;

import co.com.sofka.crud.components.Items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryItems extends CrudRepository<Items, Long> {
}