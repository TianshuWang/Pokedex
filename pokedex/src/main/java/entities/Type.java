package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "type")
public class Type extends PersistentEntity{
    @Column(name = "name")
    private String name;

    private Type(){
    }

    public Type(String name){
        this.name = name;
    }

    //getters and setters

    public String getName() {
        return name;
    }
}
