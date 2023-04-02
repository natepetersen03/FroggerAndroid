package com.x20.frogger.game.tiles;
import com.badlogic.gdx.math.Vector2;
import com.x20.frogger.game.Entity;
import com.x20.frogger.game.mobs.Creeper;
import com.x20.frogger.game.mobs.Golem;
import com.x20.frogger.game.mobs.Mob;
import com.x20.frogger.game.mobs.Skeleton;

import java.util.ArrayList;

import com.x20.frogger.game.Entity;

import java.util.LinkedList;

public class TileMap {
    // todo: add vehicle spawns, player spawn
    // todo: data structure for storing vehicles for efficient collision tests
    // ? should the above information be contained in a separate World class?
    // ? if so, World would be responsible for:
    // ? TileMap init, Player init, Vehicle init
    // ? But would it also handle GameLogic? Should GameLogic do what World would?
    // ?    Probably not. GameLogic will have to pull Player and Vehicle data from the World
    // ?    World contains all data. GameLogic manipulates/interacts with this data
    // ? further note: point assignments could be handled in many ways. some ideas:
    // ?    1. in the World class, hold an array whose length matches the height of the TileMap.
    // ?       Each value stored is the number of points received for crossing this tile.
    // ?    2. Let there be a VehicleSpawner class which determines the vehicle type, the spawning
    // ?       frequency (could be a range), and the spawn direction. It will also have position.
    // ?       This class can also contain the number of points gained by passing this spawner.
    // ?       This would assume that safe tiles don't award points (or you'd need empty spawners)
    // ? Also likely will be making that VehicleSpawner class from idea 2 anyway.
    // ? Should double check the points behavior expectations



    // access by tilemap[x][y]
    private Tile[][] tilemap;

    private ArrayList<LinkedList<Entity>> entities;
    private LinkedList<Entity>[] rowEntitiesArray;

    public TileMap() {

    }

    public int getWidth() {
        return tilemap.length;
    }

    public int getHeight() {
        return tilemap[0].length;
    }

    public ArrayList<LinkedList<Entity>> getEntities() { return entities; }
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return tilemap[x][y];
    }

    public LinkedList<Entity> getEntitiesAtRow(int rowIndex) {
        return rowEntitiesArray[rowIndex];
    }

    // MUST HAVE A PERFECTLY RECTANGULAR STRING OR BAD THINGS MIGHT HAPPEN

    /**
     * Populate the tilemap using an array of strings which indicate the tile type
     * Precondition: Each string in the array are of equal length
     * @param str Array of tile strings
     */
    public void generateTileMapFromStringArray(String[] str) {
        tilemap = new Tile[str[0].length()][str.length];
        for (int x = 0; x < str[0].length(); x++) {
            for (int y = 0; y < str.length; y++) {
                // have to go in this order or the map is upside down
                tilemap[x][str.length - y - 1] = generateTileFromChar(str[y].charAt(x));
            }
        }
        // Entity array init
        rowEntitiesArray = new LinkedList[getHeight()];
        for (int i = 0; i < rowEntitiesArray.length; i++) {
            rowEntitiesArray[i] = new LinkedList<Entity>();
        }
    }

    private Tile generateTileFromChar(char charAt) {
        return TileDatabase.getDatabase().get(TileDatabase.getCharToKey().get(charAt));
    }

    /**
     * Get the corresponding array of tile strings for an existing tilemap
     * @return the string array representation of the tilemap
     */
    public String[] generateStringArrayFromTileMap() {
        String[] str = new String[tilemap[0].length];
        char[] row = new char[tilemap.length];
        for (int y = 0; y < tilemap[0].length; y++) {
            for (int x = 0; x < tilemap.length; x++) {
                // have to go in this order or the string ends up upside down
                row[x] = TileDatabase.getKeyToChar()
                    .get(tilemap[x][str.length - y - 1].getTileData().getName());
            }
            str[y] = String.valueOf(row);
        }
        return str;
    }

    public void generateMobs() {

        entities = new ArrayList<>();

        entities.add(new LinkedList<Entity>());
        entities.get(0).add(new Creeper(0, 1));
        entities.get(0).add(new Creeper(5, 1));
        entities.get(0).add(new Creeper(10, 1));


        entities.add(new LinkedList<Entity>());
        entities.get(0).add(new Golem(0, 2));
        entities.get(0).add(new Golem(5, 2));
        entities.get(0).add(new Golem(10, 2));

        entities.add(new LinkedList<Entity>());
        entities.get(0).add(new Creeper(0, 4));
        entities.get(0).add(new Creeper(5, 4));
        entities.get(0).add(new Creeper(10, 4));

        entities.add(new LinkedList<Entity>());
        entities.get(0).add(new Skeleton(0, 5));
        entities.get(0).add(new Skeleton(5, 5));
        entities.get(0).add(new Skeleton(10, 5));

        entities.add(new LinkedList<Entity>());
        entities.get(0).add(new Golem(0, 6));
        entities.get(0).add(new Golem(5, 6));
        entities.get(0).add(new Golem(10, 6));
    }

    public String toString() {
        return String.join("\n", generateStringArrayFromTileMap());
    }
}
