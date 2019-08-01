package byow.Core;

import byow.TileEngine.TETile;


public class World {
    private int lockedDoorX;
    private int lockedDoorY;
    private int playerX;
    private int playerY;
    private TETile[][] world;

    public World(int lockedDoorX, int lockedDoorY, int playerX, int playerY, TETile[][] world) {
        this.lockedDoorX = lockedDoorX;
        this.lockedDoorY = lockedDoorY;
        this.playerX = playerX;
        this.playerY = playerY;
        this.world = world;
    }

    public int lockedDoorX() {
        return this.lockedDoorX;
    }

    public int lockedDoorY() {
        return this.lockedDoorY;
    }

    public int playerX() {
        return this.playerX;
    }

    public int playerY() {
        return this.playerY;
    }

    public TETile[][] world() {
        return this.world;
    }
}
