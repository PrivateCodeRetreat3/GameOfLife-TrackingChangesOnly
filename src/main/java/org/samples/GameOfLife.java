package org.samples;

import org.lambda.utils.Grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLife {
    private ArrayList<Point> changes = new ArrayList<>();
    private ArrayList<Point> board = new ArrayList<>();

    public GameOfLife(Point... points) {
        board.addAll(Arrays.asList(points));
        changes.addAll(Arrays.asList(points));
    }

    public void advance() {
        board = new ArrayList<>();
        changes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return Grid.print(5,7,(x,y)-> isAlive(x,y) ? "X":"_");
    }

    private boolean isAlive(Integer x, Integer y) {
        return board.contains(new Point(x,y));
    }
}
