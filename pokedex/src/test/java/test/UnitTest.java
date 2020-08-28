package test;

import manager.CRUD;
import entities.*;
import entities.exception.CreationException;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.DEFAULT)
public class UnitTest {

    private void configurationOfTypesAbilitys(){
        Type electric = new Type("Electric");
        Type water = new Type("Water");
        Type rock = new Type("Rock");
        Type ground = new Type("Ground");
        Type normal = new Type("Normal");
        Type fire = new Type("Fire");
        Type grass = new Type("Grass");
        Type poison = new Type("Poison");

        Ability electricity = new Ability("Electricity");
        Ability torrent = new Ability("Torrent");
        Ability runAway = new Ability("Run Away");
        Ability rockHead = new Ability("Rock Head");
        Ability blaze = new Ability("Blaze");
        Ability pickup = new Ability("Pickup");
        Ability damp = new Ability("Damp");
        Ability overgrow = new Ability("Overgrow");

        CRUD.addTypes(electric,water,rock,ground,normal,fire,grass,poison);
        CRUD.addAbilities(electricity,torrent,runAway,rockHead,blaze,pickup,damp,overgrow);
    }

    @Test
    public void configurationOfPokemons() throws CreationException{
        this.configurationOfTypesAbilitys();
        Type electric = CRUD.findType("Electric");
        Type water = CRUD.findType("Water");
        Type rock = CRUD.findType("Rock");
        Type ground = CRUD.findType("Ground");
        Type normal = CRUD.findType("Normal");
        Type fire = CRUD.findType("Fire");
        Type grass = CRUD.findType("Grass");
        Type poison = CRUD.findType("Poison");

        Ability electricity = CRUD.findAbility("Electricity");
        Ability torrent = CRUD.findAbility("Torrent");
        Ability runAway = CRUD.findAbility("Run Away");
        Ability rockHead = CRUD.findAbility("Rock Head");
        Ability blaze = CRUD.findAbility("Blaze");
        Ability pickup = CRUD.findAbility("Pickup");
        Ability damp = CRUD.findAbility("Damp");
        Ability overgrow = CRUD.findAbility("Overgrow");

        //configuration of evolutions
        Evolution pikachu = new Evolution.Builder().addName("Pikachu").addRequireLevel(16).addTypes(electric).build();
        Evolution wartortle = new Evolution.Builder().addName("Wartortle").addRequireLevel(20).addTypes(water).build();
        Evolution golduck = new Evolution.Builder().addName("Golduck").addRequireLevel(22).addTypes(water).build();
        Evolution grabeler = new Evolution.Builder().addName("Grabeler").addRequireLevel(15).addTypes(rock,ground).build();
        Evolution steelix = new Evolution.Builder().addName("Steelix").addRequireLevel(22).addTypes(rock,ground).build();
        Evolution marowak = new Evolution.Builder().addName("Marowak").addRequireLevel(23).addTypes(ground).build();
        Evolution vaporeon = new Evolution.Builder().addName("Vaporeon").addRequireLevel(13).addTypes(water).build();
        Evolution flareon = new Evolution.Builder().addName("Flareon").addRequireLevel(22).addTypes(fire).build();
        Evolution leafeon = new Evolution.Builder().addName("Leafeon").addRequireLevel(18).addTypes(grass).build();
        Evolution lvysaur = new Evolution.Builder().addName("Lvysaur").addRequireLevel(17).addTypes(grass,poison).build();
        Evolution charmeleon = new Evolution.Builder().addName("Charmeleon").addRequireLevel(21).addTypes(fire).build();
        Evolution persian = new Evolution.Builder().addName("Persian").addRequireLevel(33).addTypes(normal).build();

        //configuration of pokemons
        Pokemon pichu = new Pokemon.Builder().addName("Pichu").addLevel(0).addTypes(electric).addAbilities(electricity).addEvolutions(pikachu).build();
        Pokemon squirtle = new Pokemon.Builder().addName("Squirtle").addLevel(5).addTypes(water).addAbilities(torrent).addEvolutions(wartortle).build();
        Pokemon psyduck = new Pokemon.Builder().addName("Psyduck").addLevel(10).addTypes(water).addAbilities(damp).addEvolutions(golduck).build();
        Pokemon geodude = new Pokemon.Builder().addName("Geodude").addLevel(3).addTypes(rock,ground).addAbilities(rockHead).addEvolutions(grabeler).build();
        Pokemon onix = new Pokemon.Builder().addName("Onix").addLevel(10).addTypes(rock,ground).addAbilities(rockHead).addEvolutions(steelix).build();
        Pokemon cubone = new Pokemon.Builder().addName("Cubone").addLevel(5).addTypes(ground).addAbilities(rockHead).addEvolutions(marowak).build();
        Pokemon eevee = new Pokemon.Builder().addName("Eevee").addLevel(2).addTypes(normal).addAbilities(runAway).addEvolutions(vaporeon,flareon,leafeon).build();
        Pokemon bulbasaur = new Pokemon.Builder().addName("Bulbasaur").addLevel(3).addTypes(grass,poison).addAbilities(overgrow).addEvolutions(lvysaur).build();
        Pokemon charmander = new Pokemon.Builder().addName("Charmander").addLevel(7).addTypes(fire).addAbilities(blaze).addEvolutions(charmeleon).build();
        Pokemon meowth = new Pokemon.Builder().addName("Meowth").addLevel(6).addTypes(normal).addAbilities(pickup).addEvolutions(persian).build();

        CRUD.addPokemons(pichu,squirtle,psyduck,geodude,onix,cubone,eevee,bulbasaur,charmander,meowth);
    }

    @Test
    public void readPokemons(){
        List<Pokemon> pokemonList = CRUD.findAllPokemons();
        Assert.assertEquals(10,pokemonList.size());

        Pokemon p1 = pokemonList.get(0);
        Assert.assertEquals("Pichu",p1.getName());
        Pokemon p5 = pokemonList.get(5);
        Assert.assertEquals("Cubone",p5.getName());
        Pokemon p9 = pokemonList.get(9);
        Assert.assertEquals("Meowth",p9.getName());
    }

    @Test
    public void readNameTypesLevelOfCharmander(){
        Pokemon pokemon = CRUD.findPokemon("Charmander");

        Assert.assertEquals("Charmander",pokemon.getName());
        Assert.assertEquals(7,pokemon.getLevel());

        Assert.assertEquals(1,pokemon.getTypes().size());
        Type type = pokemon.getTypes().get(0);
        Assert.assertEquals("Fire",type.getName());
    }

    @Test
    public void readAbilitiesEvolutionsOfPichu(){
        Pokemon pokemon = CRUD.findPokemon("Pichu");
        Assert.assertEquals("Pichu",pokemon.getName());

        Assert.assertEquals(1,pokemon.getAbilities().size());
        Ability ability = pokemon.getAbilities().get(0);
        Assert.assertEquals("Electricity",ability.getName());

        Assert.assertEquals(1,pokemon.getEvolutions().size());
        Evolution evolution = pokemon.getEvolutions().get(0);
        Assert.assertEquals("Pikachu",evolution.getName());
    }

    @Test
    public void readEvolutionsInformationOfPichu(){
        Pokemon pokemon = CRUD.findPokemon("Pichu");
        Assert.assertEquals("Pichu",pokemon.getName());

        Assert.assertEquals(1,pokemon.getEvolutions().size());
        Evolution evolution = pokemon.getEvolutions().get(0);
        Assert.assertEquals("Pikachu",evolution.getName());
        Assert.assertEquals(16,evolution.getRequiredLevel());

        Assert.assertEquals(1,evolution.getTypes().size());
        Type type = evolution.getTypes().get(0);
        Assert.assertEquals("Electric",type.getName());
    }

    @Test
    public void addNewFoundPokemonRattata() throws CreationException {
        Type normal = CRUD.findType("Normal");
        Ability runAway = CRUD.findAbility("Run Away");

        Pokemon rattata = new Pokemon.Builder().addName("Rattata").addLevel(12).addTypes(normal).addAbilities(runAway).build();
        CRUD.addPokemons(rattata);

        Pokemon pokemon = CRUD.findPokemon("Rattata");
        Assert.assertEquals("Rattata",pokemon.getName());
        Assert.assertEquals(12,pokemon.getLevel());
    }

    @Test
    public void updateNamePPPPichuToPichu(){
        CRUD.updatePokemonName("Pichu","PPPPichu");

        Pokemon pokemon = CRUD.findPokemon("PPPPichu");
        Assert.assertEquals("PPPPichu",pokemon.getName());
    }

    @Test
    public void addTypeCatToMeowth(){
        Pokemon pokemon = CRUD.findPokemon("Meowth");
        Assert.assertEquals(1,pokemon.getTypes().size());

        CRUD.addPokemonType("Meowth",new Type("Cat"));
        Assert.assertEquals(2,pokemon.getTypes().size());
        Assert.assertEquals("Cat",pokemon.getTypes().get(1).getName());
    }

    @Test
    public void addEvolutionGlaceonToEevee() throws CreationException {
        Pokemon pokemon = CRUD.findPokemon("Eevee");
        Assert.assertEquals(3,pokemon.getEvolutions().size());

        Evolution glaceon = new Evolution.Builder().addName("Glaceon").addRequireLevel(10).addTypes(new Type("Ice")).build();
        CRUD.addPokemonEvolution("Eevee",glaceon);
        Assert.assertEquals(4,pokemon.getEvolutions().size());
        Assert.assertEquals("Glaceon",pokemon.getEvolutions().get(3).getName());
    }

    @Test
    public void removeTypeRockOfOnix(){
        Pokemon pokemon = CRUD.findPokemon("Onix");
        Assert.assertEquals("Rock",pokemon.getTypes().get(0).getName());

        CRUD.removePokemonType("Onix","Rock");
        Assert.assertEquals("Ground",pokemon.getTypes().get(0).getName());
    }
}
