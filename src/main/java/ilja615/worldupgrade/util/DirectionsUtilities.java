package ilja615.worldupgrade.util;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;

import java.util.Random;

public class DirectionsUtilities {
    public static final Direction[] DIRECTIONS_4h = new Direction[]{Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH};

    public static Direction.Axis getDirectionAxisFromDirection(Direction direction)
    {
        switch (direction) {
            case NORTH: return Direction.Axis.Z;
            case SOUTH: return Direction.Axis.Z;
            case EAST: return Direction.Axis.X;
            case WEST: return Direction.Axis.X;
            case UP: return Direction.Axis.Y;
            case DOWN: return Direction.Axis.Y;
            default: return Direction.Axis.Y;
        }
    }

    public static Direction getClockWise(Direction direction)
    {
        switch (direction) {
            case NORTH: return Direction.EAST;
            case SOUTH: return Direction.WEST;
            case EAST: return Direction.SOUTH;
            case WEST: return Direction.NORTH;
            default: return Direction.NORTH;
        }
    }

    public static Direction getCounterClockWise(Direction direction)
    {
        switch (direction) {
            case NORTH: return Direction.WEST;
            case SOUTH: return Direction.EAST;
            case EAST: return Direction.NORTH;
            case WEST: return Direction.SOUTH;
            default: return Direction.NORTH;
        }
    }

    public static Direction getRandomMossGrowthDirection(Direction direciton, Random rand)
    {
        int i = rand.nextInt(4);
        switch (direciton)
        {
            case NORTH:
                if (i == 0) return Direction.EAST;
                if (i == 1) return Direction.WEST;
                if (i == 2) return Direction.UP;
                if (i == 3) return Direction.DOWN;
            case SOUTH:
                if (i == 0) return Direction.EAST;
                if (i == 1) return Direction.WEST;
                if (i == 2) return Direction.UP;
                if (i == 3) return Direction.DOWN;
            case EAST:
                if (i == 0) return Direction.NORTH;
                if (i == 1) return Direction.SOUTH;
                if (i == 2) return Direction.UP;
                if (i == 3) return Direction.DOWN;
            case WEST:
                if (i == 0) return Direction.NORTH;
                if (i == 1) return Direction.SOUTH;
                if (i == 2) return Direction.UP;
                if (i == 3) return Direction.DOWN;
            case UP:
                if (i == 0) return Direction.EAST;
                if (i == 1) return Direction.WEST;
                if (i == 2) return Direction.NORTH;
                if (i == 3) return Direction.SOUTH;
            case DOWN:
                if (i == 0) return Direction.EAST;
                if (i == 1) return Direction.WEST;
                if (i == 2) return Direction.NORTH;
                if (i == 3) return Direction.SOUTH;
        }
        // default
        System.out.println("No valid direction has been picked for the moss to grow to. this might cause weird behaviour. Falling back to use the default direction: DOWN.");
        return Direction.DOWN;
    }
}
