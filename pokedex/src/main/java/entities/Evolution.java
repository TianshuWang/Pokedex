package entities;

import entities.exception.CreationException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "evolution")
public class Evolution extends PersistentEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "requiredLevel")
    private Integer requiredLevel;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Type> types;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    private Pokemon pokemon;

    private Evolution(){

    }

    public Evolution(Builder builder){
        this.name = builder.name;
        this.requiredLevel = builder.requiredLevel;
        this.types = builder.types;
        this.pokemon = builder.pokemon;
    }

    public static class Builder{
        private String name;
        private Integer requiredLevel;
        private List<Type> types;
        private Pokemon pokemon;

        public Builder(){
            this.types = new ArrayList<>();
        }

        public Builder addName(String name){
            this.name = name;
            return this;
        }

        public Builder addRequireLevel(Integer level){
            this.requiredLevel = level;
            return this;
        }

        public Builder addTypes(Type ...types){
            Collections.addAll(this.types,types);
            return this;
        }

        public Builder addPokemon(Pokemon pokemon){
            this.pokemon = pokemon;
            return this;
        }

        public Evolution build() throws CreationException {
            if(this.name == null){
                throw new CreationException("There is no name assigned");
            }
            return new Evolution(this);
        }

    }

    //getters and setters

    public String getName() {
        return name;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
