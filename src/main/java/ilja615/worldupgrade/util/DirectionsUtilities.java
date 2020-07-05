package ilja615.worldupgrade.util;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;

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
}
