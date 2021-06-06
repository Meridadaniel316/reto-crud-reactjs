package co.com.sofka.crud.repositories;

import co.com.sofka.crud.components.Groups;
import co.com.sofka.crud.components.Items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryModel extends CrudRepository<Groups, Long> {
    Iterable<Items> findAllToDosById(Long id);
}