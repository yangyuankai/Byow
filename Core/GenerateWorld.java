package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class GenerateWorld {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;



    // generate a world (not random) with one player and a locked door in it -- based on seed
    public static World generate(String input) {


        // 1
        String numberOnly = input.replaceAll("[^0-9]", "");
        Long seed = Long.parseLong(numberOnly);


        Random r = new Random(seed);



        // 2
        TETile[][] w = new TETile[WIDTH][HEIGHT];

        // create blank space
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                w[x][y] = Tileset.NOTHING;
            }
        }

        // create rooms and hallways
        int[] start = createRoom(w, r.nextInt());
        for (int i = 0; i < 32; i++) { // number of i represents the number of rooms to create
            int[] end = createRoom(w, r.nextInt());
            if (end != null) {

                // gets 2 random points inside two rooms to connect a hallway
                int v = r.nextInt();
                int startX = grbpiar(start[0], start[1], start[2], start[3], v)[0];
                int startY = grbpiar(start[0], start[1], start[2], start[3], v)[1];
                int endX = grbpiar(end[0], end[1], end[2], end[3], v)[0];
                int endY = grbpiar(end[0], end[1], end[2], end[3], v)[1];

                createHallway(w, startX, startY, endX, endY, r.nextInt());

                start = end;
            }
        }


        // finally, create walls
        createWalls(w);


        //////////////////////////////////////////////////////////
        //// ADD players and other stuff
        //////////////////////////////////////////////////////////
        ArrayList<Integer> floorXs = new ArrayList<>();
        ArrayList<Integer> floorYs = new ArrayList<>();
        ArrayList<Integer> wallXs = new ArrayList<>();
        ArrayList<Integer> wallYs = new ArrayList<>();


        for (int x = 1; x < w.length - 1; x += 1) {
            for (int y = 1; y < w[0].length - 1; y += 1) {
                TETile upper = w[x][y + 1];
                TETile lower = w[x][y - 1];
                TETile left = w[x - 1][y];
                TETile right = w[x + 1][y];
                boolean isOutside1 = upper.description().equals("nothing");
                boolean isOutside2 = lower.description().equals("nothing");
                boolean isOutside3 = left.description().equals("nothing");
                boolean isOutside4 = right.description().equals("nothing");
                boolean isDoor = (isOutside1 || isOutside2 || isOutside3 || isOutside4);


                if (w[x][y].description().equals("floor")) {
                    floorXs.add(x);
                    floorYs.add(y);
                }
                if (w[x][y].description().equals("wall") && isDoor) {
                    wallXs.add(x);
                    wallYs.add(y);
                }
            }
        }




        int playerX = floorXs.get(floorXs.size() / 2);
        int playerY = floorYs.get(floorXs.size() / 2);
        int lockedX1 = wallXs.get(wallXs.size() / 2);
        int lockedY1 = wallYs.get(wallXs.size() / 2);
        int unlockedX1 = wallXs.get(wallXs.size() / 3);
        int unlockedY1 = wallYs.get(wallXs.size() / 3);
        int unlockedX2= wallXs.get(wallXs.size() / 4);
        int unlockedY2 = wallYs.get(wallXs.size() / 4);


        // now randomly select player location and locked door location on the world
        w[playerX][playerY] = Tileset.AVATAR;
        w[lockedX1][lockedY1] = Tileset.LOCKED_DOOR;
        w[unlockedX1][unlockedY1] = Tileset.UNLOCKED_DOOR;
        w[unlockedX2][unlockedY2] = Tileset.UNLOCKED_DOOR;

        return new World(lockedX1, lockedY1, playerX, playerY, w);


    }



















    ////////////////////////////////////////////////////////////////////////////
    ////                        HELPER METHODS I                            ////
    ////////////////////////////////////////////////////////////////////////////



    private static int[] createRoom(TETile[][] world, int r) {
        // create random width between 2 and 8
        int width = Math.floorMod(r, 9);
        if (width < 2) {
            width += 7;
        }

        // create random height between 2 and 6
        int height = Math.floorMod(r, 7);
        if (height < 2) {
            height += 5;
        }

        // select a random location point (x, y) in the world
        int locationX = Math.floorMod(r, WIDTH);
        int locationY = Math.floorMod(r, HEIGHT);
        // make sure that location x, y is not going to result in a room out of boundary:
        if (locationX + 2 * width >= WIDTH) {
            locationX = locationX / 2;
        }
        if (locationY + 2 * height >= HEIGHT) {
            locationY = locationY / 2;
        }

        if (locationX <= 3) {
            locationX = locationX + 8;
        }
        if (locationY <= 3) {
            locationY = locationY + 4;
        }

        // If location already exists a room, do nothing
        if (world[locationX][locationY].description().equals("floor")) {
            return null;
        }

        // from (x, y) to (x + width, y + height) we create a room
        // (x, y) is the lower left corner
        // (x + width, y + height) is the upper right corner
        for (int x = locationX; x < locationX + width; x += 1) {
            for (int y = locationY; y < locationY + height; y += 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }
        return new int[]{locationX, locationY, width, height};
    }









    private static void createHallway(TETile[][] world, int startX,
                                      int startY, int endX, int endY, int r) {
        if (startX == endX) {
            if (Math.floorMod(r, 217) == 0) { // 1 in every 217 chance to create straight hallways
                createVerticalHallway(world, startX, startY, endY);
            } else {
                // first create hallway to the right
                createHorizontalHallway(world, startX, startY, startX + Math.floorMod(r, 8));
                if (startX + Math.floorMod(r, 8) >= WIDTH) { // case when reaching boundary
                    createVerticalHallway(world, WIDTH - 1, startY, endY); //could be up or down
                    createHorizontalHallway(world, WIDTH - 1, endY, startX); //left
                } else { // normal cases
                    createVerticalHallway(world, startX + Math.floorMod(r, 8), startY, endY);
                    createHorizontalHallway(world, startX + Math.floorMod(r, 8), endY, startX);
                }
            }
        } else if (startY == endY) {
            if (Math.floorMod(r, 217) == 0) {
                createHorizontalHallway(world, startX, startY, endX);
            } else {
                // first create hallway up
                createVerticalHallway(world, startX, startY, startY + Math.floorMod(r, 4));
                if (startY + Math.floorMod(r, 4) >= HEIGHT) { // case when reaching boundary
                    createHorizontalHallway(world, startX, HEIGHT - 1, endX); //right or left
                    createVerticalHallway(world, endX, HEIGHT - 1, startY); //down
                } else { // normal cases
                    createHorizontalHallway(world, startX, startY + Math.floorMod(r, 4), endX);
                    createVerticalHallway(world, endX, startY + Math.floorMod(r, 4), startY);
                }
            }



        } else { // when start and end are NOT on the same vertical line
            if (Math.floorMod(r, 2) == 0) {
                createHorizontalHallway(world, startX, startY, endX);
                createVerticalHallway(world, endX, startY, endY);
            } else {
                createVerticalHallway(world, startX, startY, endY);
                createHorizontalHallway(world, startX, endY, endX);
            }

        }


    }




    private static void createVerticalHallway(TETile[][] world, int startX, int startY, int endY) {
        for (int i = 0; i < Math.abs(endY - startY) + 1; i++) {
            if (startY <= endY) {
                if (startY + i >= HEIGHT) {
                    return;
                }
                world[startX][startY + i] = Tileset.FLOOR;
            } else {
                if (startY - i <= 0) {
                    return;
                }
                world[startX][startY - i] = Tileset.FLOOR;
            }

        }
    }

    private static void createHorizontalHallway(TETile[][] world, int startX, int startY, int endX) {
        for (int i = 0; i < Math.abs(endX - startX) + 1; i++) {
            if (startX <= endX) {
                if (startX + i >= WIDTH) {
                    return;
                }
                world[startX + i][startY] = Tileset.FLOOR;
            } else {
                if (startX - i <= 0) {
                    return;
                }
                world[startX - i][startY] = Tileset.FLOOR;
            }

        }
    }


    private static void createWalls(TETile[][] world) {
        for (int i = 1; i < WIDTH - 1; i++) {
            for (int j = 1; j < HEIGHT - 1; j++) {
                if (world[i][j].description().equals("floor")) {
                    if (world[i + 1][j].description().equals("nothing")) {
                        world[i + 1][j] = Tileset.WALL;
                    }
                    if (world[i - 1][j].description().equals("nothing")) {
                        world[i - 1][j] = Tileset.WALL;
                    }
                    if (world[i][j + 1].description().equals("nothing")) {
                        world[i][j + 1] = Tileset.WALL;
                    }
                    if (world[i][j - 1].description().equals("nothing")) {
                        world[i][j - 1] = Tileset.WALL;
                    }

                    if (world[i + 1][j + 1].description().equals("nothing")) {
                        world[i + 1][j + 1] = Tileset.WALL;
                    }
                    if (world[i + 1][j - 1].description().equals("nothing")) {
                        world[i + 1][j - 1] = Tileset.WALL;
                    }
                    if (world[i - 1][j + 1].description().equals("nothing")) {
                        world[i - 1][j + 1] = Tileset.WALL;
                    }
                    if (world[i - 1][j - 1].description().equals("nothing")) {
                        world[i - 1][j - 1] = Tileset.WALL;
                    }
                }
            }
        }
    }




    // get random boundary point in a room (set as the start of the hallway)
    private static int[] grbpiar(int locationX, int locationY, int w, int h, int r) {
        int xValue = locationX + Math.floorMod(r, w);
        int yValue = locationY + Math.floorMod(r, h);
        return new int[]{xValue, yValue};
    }





}
