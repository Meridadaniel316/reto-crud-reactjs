package co.com.sofka.crud.components;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="groups")
public class Groups {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Items> ListItems;

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

    public Set<Items> getToDos() {
        return ListItems;
    }

    public void setToDos(Set<Items> toDos) {
        this.ListItems = ListItems;
    }
}
