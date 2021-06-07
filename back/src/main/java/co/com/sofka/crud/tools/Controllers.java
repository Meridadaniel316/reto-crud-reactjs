package co.com.sofka.crud.tools;

import co.com.sofka.crud.models.ModelGroup;
import co.com.sofka.crud.models.ModelItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})

public class Controllers {

    private final Services services;

    @Autowired
    public Controllers(Services services) {
        this.services = services;
    }

    @GetMapping(value = "api/list")
    public Iterable<ModelGroup> getAllListToDos(){
        return services.getAllListToDos();
    }

    @GetMapping(value = "api/{listId}/todos")
    public Iterable<ModelItems> getToDosByListId(@PathVariable("listId") Long listId){
        return services.getToDosByListId(listId);
    }

    @PostMapping(value = "api/todolist")
    public ModelGroup newListToDo(@RequestBody ModelGroup todo){
        return services.newListToDo(todo);
    }

    @DeleteMapping(value = "api/{id}/todolist")
    public void deleteListById(@PathVariable("id") Long id){
        services.deleteListById(id);
    }

    @PutMapping(value = "api/{listId}/todo")
    public ModelItems updateAToDoByListId(@PathVariable("listId") Long listId, @RequestBody ModelItems todo){
        if(todo.getId() != null){
            return services.updateAToDoByListId(listId, todo);
        }
        throw new Error("No existe el id para actualizar");
    }

    @PostMapping(value = "api/{listId}/todo")
    public ModelItems addNewToDoByListId(@PathVariable("listId") Long listId, @RequestBody ModelItems todo){
        return services.addNewToDoByListId(listId, todo);
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void deleteAToDoById(@PathVariable("id")Long id){
        services.deleteAToDoById(id);
    }

}