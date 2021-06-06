package co.com.sofka.crud.tools;

import co.com.sofka.crud.repositories.RepositoryItems;
import co.com.sofka.crud.components.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {

    @Autowired
    private RepositoryItems repository;

    public Iterable<Items> list(){
        return repository.findAll();
    }

    public Items save(Items todo){
        return repository.save(todo);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Items get(Long id){
         return repository.findById(id).orElseThrow();
    }

}
