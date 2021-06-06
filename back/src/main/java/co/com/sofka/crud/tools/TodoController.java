package co.com.sofka.crud.tools;

import co.com.sofka.crud.components.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private Services service;

    @GetMapping(value = "api/todos")
    public Iterable<Items> list(){
        return service.list();
    }

    @PostMapping(value = "api/todo")
    public Items save(@RequestBody Items todo){
        return service.save(todo);
    }

    @PutMapping(value = "api/todo")
    public Items update(@RequestBody Items todo){
        if(todo.getId() != null){
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public Items get(@PathVariable("id") Long id){
        return service.get(id);
    }

}
