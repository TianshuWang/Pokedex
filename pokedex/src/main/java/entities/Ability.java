package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ability")
public class Ability extends PersistentEntity{
    @Column(name = "name")
    private String name;

    private Ability(){

    }

    public Ability(String name){
        this.name = name;
    }

    //getters and setters

    public String getName() {
        return name;
    }
}
