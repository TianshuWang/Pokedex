package test;

import manager.CRUD;
import entities.*;
import entities.exception.CreationException;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.DEFAULT)
public class UnitTest {

    @Test
    public void configurationOfPokemons() throws CreationException{
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

        Pokemon pichu = pokemonList.get(0);
        Assert.assertEquals("Pichu",pichu.getName());
        Pokemon geodude = pokemonList.get(3);
        Assert.assertEquals("Geodude",geodude.getName());
        Pokemon cubone = pokemonList.get(5);
        Assert.assertEquals("Cubone",cubone.getName());
        Pokemon meowth = pokemonList.get(9);
        Assert.assertEquals("Meowth",meowth.getName());
    }

    @Test
    public void readNameTypesLevelOfCharmander(){
        Pokemon charmander = CRUD.findPokemon("Charmander");

        Assert.assertEquals("Charmander",charmander.getName());
        Assert.assertEquals(7,charmander.getLevel());

        Assert.assertEquals(1,charmander.getTypes().size());
        Type fire = charmander.getTypes().get(0);
        Assert.assertEquals("Fire",fire.getName());
    }

    @Test
    public void readAbilitiesEvolutionsOfSquirtle(){
        Pokemon squirtle = CRUD.findPokemon("Squirtle");
        Assert.assertEquals("Squirtle",squirtle.getName());

        Assert.assertEquals(1,squirtle.getAbilities().size());
        Ability torrent = squirtle.getAbilities().get(0);
        Assert.assertEquals("Torrent",torrent.getName());

        Assert.assertEquals(1,squirtle.getEvolutions().size());
        Evolution wartortle = squirtle.getEvolutions().get(0);
        Assert.assertEquals("Wartortle",wartortle.getName());
    }

    @Test
    public void readEvolutionsInformationOfPichu(){
        Pokemon pichu = CRUD.findPokemon("Pichu");
        Assert.assertEquals("Pichu",pichu.getName());

        Assert.assertEquals(1,pichu.getEvolutions().size());
        Evolution pikachu = pichu.getEvolutions().get(0);
        Assert.assertEquals("Pikachu",pikachu.getName());
        Assert.assertEquals(16,pikachu.getRequiredLevel());

        Assert.assertEquals(1,pikachu.getTypes().size());
        Type electric = pikachu.getTypes().get(0);
        Assert.assertEquals("Electric",electric.getName());
    }

    @Test
    public void addNewFoundPokemonRattata() throws CreationException {
        Type normal = CRUD.findType("Normal");
        Ability runAway = CRUD.findAbility("Run Away");

        //operation
        Pokemon rattata = new Pokemon.Builder().addName("Rattata").addLevel(12).addTypes(normal).addAbilities(runAway).build();
        CRUD.addPokemons(rattata);

        //after
        Pokemon rattataa = CRUD.findPokemon("Rattata");
        Assert.assertEquals("Rattata",rattataa.getName());
        Assert.assertEquals(12,rattataa.getLevel());
    }

    @Test()
    public void updatePichuName(){
        //before
        Pokemon pichu = CRUD.findPokemon("Pichu");
        Assert.assertEquals("Pichu",pichu.getName());

        //operation
        CRUD.updatePokemonName("Pichu","PPPPichu");

        //after
        Pokemon ppppichu = CRUD.findPokemon("PPPPichu");
        Assert.assertEquals("PPPPichu",ppppichu.getName());
    }

    @Test
    public void updateCharmanderLevel(){
        //before
        Pokemon charmander = CRUD.findPokemon("Charmander");
        Assert.assertEquals(7,charmander.getLevel());

        //operation
        CRUD.updatePokemonLevel(charmander.getName(),11);

        //after
        Pokemon charmanderr = CRUD.findPokemon("Charmander");
        Assert.assertEquals(11,charmanderr.getLevel());
    }

    @Test
    public void addTypeCatToMeowth(){
        //before
        Pokemon meowth = CRUD.findPokemon("Meowth");
        Assert.assertEquals(1,meowth.getTypes().size());

        //operation
        CRUD.addPokemonType("Meowth",new Type("Cat"));

        //after
        Pokemon meowthh = CRUD.findPokemon("Meowth");
        Type cat = meowthh.getTypes().get(1);
        Assert.assertEquals("Cat",cat.getName());
    }

    @Test
    public void addEvolutionGlaceonToEevee() throws CreationException {
        //before
        Pokemon eevee = CRUD.findPokemon("Eevee");
        Assert.assertEquals(3,eevee.getEvolutions().size());

        //operation
        Evolution glaceon = new Evolution.Builder().addName("Glaceon").addRequireLevel(10).addTypes(new Type("Ice")).build();
        CRUD.addPokemonEvolution("Eevee",glaceon);

        //after
        Pokemon eeveee = CRUD.findPokemon("Eevee");
        Evolution glaceonn = eeveee.getEvolutions().get(3);
        Assert.assertEquals("Glaceon",glaceonn.getName());
    }

    @Test
    public void removeTypeRockOfOnix(){
        //before
        Pokemon onix = CRUD.findPokemon("Onix");
        Type rock = onix.getTypes().get(0);
        Assert.assertEquals("Rock",rock.getName());

        //operation
        CRUD.removePokemonType("Onix","Rock");

        //after
        Pokemon onixx = CRUD.findPokemon("Onix");
        Type ground = onixx.getTypes().get(0);
        Assert.assertEquals("Ground",ground.getName());
    }
}
