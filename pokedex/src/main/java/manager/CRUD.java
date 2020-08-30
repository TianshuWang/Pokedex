package manager;

import entities.*;
import java.util.*;

public class CRUD {

    //configuration
    public static void addPokemons(Pokemon ...pokemons){
        addObjects(Pokemon.class,pokemons);
    }

    //modification
    public static void updatePokemonName(String name, String newName){
        Pokemon pokemon = (Pokemon) find(Pokemon.class,name);
        pokemon.setName(newName);
        update(pokemon);
    }

    public static void updatePokemonLevel(String name, Integer newLevel) {
        Pokemon pokemon = (Pokemon) find(Pokemon.class, name);
        pokemon.setLevel(newLevel);
        update(pokemon);
    }

    public static void removePokemonType(String name,String typeName){
        Pokemon pokemon = (Pokemon) find(Pokemon.class,name);

        List<Type> types = pokemon.getTypes();
        Type typeRemove = types.stream().filter(t->t.getName().equals(typeName)).findFirst().orElse(null);

        if(typeRemove != null){
            pokemon.removeType(typeRemove);
            update(pokemon);
        }
    }

    public static void addPokemonType(String name,Type type){
        Pokemon pokemon = (Pokemon) find(Pokemon.class,name);

        pokemon.addTypes(type);
        update(pokemon);
    }

    public static void addPokemonEvolution(String name, Evolution evolution){
        Pokemon pokemon = (Pokemon) find(Pokemon.class,name);

        pokemon.addEvolutions(evolution);
        update(pokemon);

        Evolution evolution1 = (Evolution) find(Evolution.class,evolution.getName());
        evolution1.setPokemon(pokemon);
        update(evolution1);
    }

    //find
    public static Type findType(String name){
        return (Type) find(Type.class,name);
    }

    public static Ability findAbility(String name){
        return (Ability) find(Ability.class,name);
    }

    public static Pokemon findPokemon(String name){
        return (Pokemon) find(Pokemon.class,name);
    }

    public static List<Pokemon> findAllPokemons(){
        return (List<Pokemon>)(List)findAll(Pokemon.class);
    }

    //private methods
    private static void addObjects(Class t,Object ...objects){
        List<Object> objectList = Arrays.asList(objects);
        objectList.forEach(o->persist(o));
    }

    private static Object find(Class t,String name){
        Object o = EntityManagerHelper
                .createQuery("from "+t.getSimpleName()+" where name = " + "'" + name + "'")
                .getSingleResult();
        return o;
    }

    private static List<Object> findAll(Class t){
        List<Object> list = EntityManagerHelper
                .createQuery("from "+t.getSimpleName())
                .getResultList();
        return list;
    }

    private static void persist(Object object){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(object);
        EntityManagerHelper.commit();
    }

    private static void update(Object object){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.merge(object);
        EntityManagerHelper.commit();
    }
}
