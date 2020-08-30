package test;

import entities.*;
import manager.CRUD;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        //list all the pokemons in the database
        List<Pokemon> pokemons = CRUD.findAllPokemons();
        for(Pokemon pokemon:pokemons){
            System.out.println("Name:"+pokemon.getName());
            System.out.println("Level:"+pokemon.getLevel());
            System.out.println("Types:");
            for(Type t:pokemon.getTypes()){
                System.out.println(t.getName());
            }
            System.out.println("---Evolutions---:");
            for(Evolution e:pokemon.getEvolutions()){
                System.out.println("Evolution's Name:"+e.getName());
                System.out.println("Required Level:"+e.getRequiredLevel());
                System.out.println("Evolution's Types:");
                for(Type t:e.getTypes()){
                    System.out.println(t.getName());
                }
            }
            System.out.println("================================");
        }

    }
}
