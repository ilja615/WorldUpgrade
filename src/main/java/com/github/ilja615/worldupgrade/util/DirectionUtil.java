package com.github.ilja615.worldupgrade.util;

import net.minecraft.util.Direction;

import java.util.Random;

public class DirectionUtil
{
    public static final Direction[] DIRECTIONS_4h = new Direction[]{Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH};

    public static Direction getClockWise(Direction direction)
    {
        try
        {
            return direction.getClockWise();
        } catch (IllegalStateException exception)
        {
            return Direction.NORTH;
        }
    }

    public static Direction getCounterClockWise(Direction direction)
    {
        try
        {
            return direction.getCounterClockWise();
        } catch (IllegalStateException exception)
        {
            return Direction.NORTH;
        }
    }

    public static Direction getRandomMossGrowthDirection(Direction direction, Random rand)
    {
        int i = rand.nextInt(4);
        switch (direction)
        {
            case NORTH:
            case SOUTH:
                if (i == 0) return Direction.EAST;
                else if (i == 1) return Direction.WEST;
                else if (i == 2) return Direction.UP;
                return Direction.DOWN;
            case EAST:
            case WEST:
                if (i == 0) return Direction.NORTH;
                else if (i == 1) return Direction.SOUTH;
                else if (i == 2) return Direction.UP;
                else return Direction.DOWN;
            case UP:
            case DOWN:
                if (i == 0) return Direction.EAST;
                else if (i == 1) return Direction.WEST;
                else if (i == 2) return Direction.NORTH;
                else return Direction.SOUTH;
        }
        // default
        System.out.println("No valid direction has been picked for the moss to grow to. this might cause weird behaviour. Falling back to use the default direction: DOWN.");
        return Direction.DOWN;
    }
}
