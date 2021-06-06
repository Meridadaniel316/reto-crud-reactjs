package co.com.sofka.crud.models;


import java.util.HashSet;
import java.util.Set;

public class ModelGroup {
    private Long id;
    private String name;
    private Set<ModelItems> items = new HashSet<>();

    public ModelGroup(){
        super();
    }
    public ModelGroup(Long id, String name, Set<ModelItems> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ModelItems> getItems() {
        return items;
    }

    public void setItems(Set<ModelItems> items) {
        this.items = items;
    }
}
