package co.com.sofka.crud.tools;

import co.com.sofka.crud.components.Groups;
import co.com.sofka.crud.models.ModelGroup;
import co.com.sofka.crud.repositories.RepositoryModel;
import co.com.sofka.crud.components.Items;
import co.com.sofka.crud.models.ModelItems;
import co.com.sofka.crud.repositories.RepositoryItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Services {

    public static final String NO_FAULT_ID = "No existe el id de la lista";

    private final RepositoryModel repositoryModel;
    private final RepositoryItems repositoryItems;

    @Autowired
    public Services(RepositoryModel repositoryModel, RepositoryItems repositoryItems) {
        this.repositoryModel = repositoryModel;
        this.repositoryItems = repositoryItems;
    }

    public Set<ModelItems> getToDosByListId(Long id) {
        return repositoryModel.findById(id)
                .orElseThrow(() -> new Error(NO_FAULT_ID))
                .getToDos().stream()
                .map(item -> new ModelItems (item.getId(), item.getName(), item.isCompleted(), id))
                .collect(Collectors.toSet());
    }

    public ModelItems addNewToDoByListId(Long listId, ModelItems aModelItems) {
        var listToDo = repositoryModel.findById(listId)
                .orElseThrow(() -> new Error(NO_FAULT_ID));
        var toDo = new Items ();

        toDo.setCompleted(aModelItems.isCompleted());
        toDo.setName(Objects.requireNonNull(aModelItems.getName()));
        toDo.setId(aModelItems.getId());

        if(toDo.getName().isEmpty() || toDo.getName().length() < 3){
            throw new Error("No hay lista de tareas válidas para guardar.");
        }

        listToDo.getToDos().add(toDo);

        var listUpdated = repositoryModel.save(listToDo);

        var lastToDo = listUpdated.getToDos()
                .stream()
                .max(Comparator.comparingInt(item -> item.getId().intValue()))
                .orElseThrow();
        aModelItems.setId(lastToDo.getId());
        aModelItems.setListId(listId);
        return aModelItems;
    }

    public ModelItems updateAToDoByListId(Long listId, ModelItems aModelItems) {
        var listToDo = repositoryModel.findById(listId)
                .orElseThrow(() -> new Error(NO_FAULT_ID));

        for(var item : listToDo.getToDos()){
            if(item.getId().equals(aModelItems.getId())){
                item.setCompleted(aModelItems.isCompleted());
                item.setName(Objects.requireNonNull(aModelItems.getName()));
                item.setId(Objects.requireNonNull(aModelItems.getId()));
            }
        }

        repositoryModel.save(listToDo);

        return aModelItems;
    }


    public ModelGroup newListToDo(ModelGroup aModelGroup) {
        var listToDo = new Groups ();
        listToDo.setName(Objects.requireNonNull(aModelGroup.getName()));
        if(listToDo.getName().isEmpty() || listToDo.getName().length() < 3){
            throw new Error("No hay lista de tareas válidas para guardar.");
        }
        var id = repositoryModel.save(listToDo).getId();
        aModelGroup.setId(id);
        return aModelGroup;
    }

    public Set<ModelGroup> getAllListToDos() {
        return StreamSupport
                .stream(repositoryModel.findAll().spliterator(), false)
                .map(groups -> {
                    var listDto = groups.getToDos()
                            .stream()
                            .map(item -> new ModelItems (item.getId(), item.getName(), item.isCompleted(), groups.getId()))
                            .collect(Collectors.toSet());
                    return new ModelGroup (groups.getId(), groups.getName(), listDto);
                })
                .collect(Collectors.toSet());
    }

    public void deleteListById(Long listId){
        var listToDo = repositoryModel.findById(listId)
                .orElseThrow(() -> new Error(NO_FAULT_ID));
        repositoryModel.delete(listToDo);
    }

    public void deleteAToDoById(Long id) {
        var toDo = repositoryItems.findById(id).orElseThrow();
        repositoryItems.delete(toDo);
    }
}
