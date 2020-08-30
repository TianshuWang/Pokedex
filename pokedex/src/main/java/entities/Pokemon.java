package entities;

import entities.exception.CreationException;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pokemon")
public class Pokemon extends PersistentEntity{
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Type> types;

    @Column(name = "level")
    private Integer level;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ability> abilities;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evolution> evolutions;

    private Pokemon(){
    }

    private Pokemon(Builder builder){
        this.name = builder.name;
        this.level = builder.level;
        this.types = builder.types;
        this.abilities = builder.abilities;
        this.evolutions = builder.evolutions;
    }

    public static class Builder{
        private String name;
        private List<Type> types;
        private Integer level;
        private List<Ability> abilities;
        private List<Evolution> evolutions;

        public Builder(){
            this.types = new ArrayList<>();
            this.abilities = new ArrayList<>();
            this.evolutions = new ArrayList<>();
            this.level = 0;
        }

        public Builder addName(String name){
            this.name = name;
            return this;
        }

        public Builder addLevel(int level){
            this.level = level;
            return this;
        }

        public Builder addTypes(Type ...types){
            Collections.addAll(this.types,types);
            return this;
        }

        public Builder addAbilities(Ability ...abilities){
            Collections.addAll(this.abilities,abilities);
            return this;
        }

        public Builder addEvolutions(Evolution ...evolutions){
            Collections.addAll(this.evolutions,evolutions);
            return this;
        }

        public Pokemon build() throws CreationException {
            if(this.name == null){
                throw new CreationException("There is no name assigned");
            }

            Pokemon p = new Pokemon(this);
            List<Evolution> evolutions = p.getEvolutions();
            evolutions.forEach(e->e.setPokemon(p));
            return p;
        }
    }

    public String getName() {
        return name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public int getLevel() {
        return level;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public List<Evolution> getEvolutions() {
        return evolutions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTypes(Type ...types) {
        Collections.addAll(this.types,types);
    }

    public void removeType(Type type){
        this.types.remove(type);
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void addEvolutions(Evolution ...evolutions) {
        Collections.addAll(this.evolutions,evolutions);
    }
}
