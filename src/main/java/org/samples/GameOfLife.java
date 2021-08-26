package org.samples;

import org.lambda.query.Query;
import org.lambda.query.Queryable;
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
        var newBoard = new ArrayList<Point>();
        newBoard.addAll(board);
        //find all the changes and their neighbors
        final ArrayList<Point> newChanges = new ArrayList<>();
        Queryable<Queryable<Point>> select = Query.select(changes, c -> getNeighbours(c));
        Queryable<Point> flatten = flatten(select);
        for (Point point : flatten) {
            int neighbours = countNeighbours(point);
            if (!isAlive(point) && neighbours == 3) {
                newChanges.add(point);
                newBoard.add(point);
            } else if (isAlive(point) && !(neighbours == 2 || neighbours == 3)) {
                newBoard.remove(point);
                newChanges.add(point);
            }
        }
        board = newBoard;
        changes = newChanges;
    }

    private int countNeighbours(Point point) {
        return getNeighbours(point).where(p -> !p.equals(point) && isAlive(p)).size();
    }

    private boolean isAlive(Point point) {
        return isAlive(point.x, point.y);
    }

    private Queryable<Point> flatten(Queryable<Queryable<Point>> select) {
        Queryable<Point> flattenedList = new Queryable<>(Point.class);
        for (Queryable<Point> points : select) {
            flattenedList.addAll(points);
        }
        return flattenedList;
    }

    private Queryable<Point> getNeighbours(Point c) {
        return Queryable.as(
                new Point(c.x - 1, c.y - 1),
                new Point(c.x - 0, c.y - 1),
                new Point(c.x + 1, c.y - 1),
                new Point(c.x - 1, c.y - 0),
                new Point(c.x - 0, c.y - 0),
                new Point(c.x + 1, c.y - 0),
                new Point(c.x - 1, c.y + 1),
                new Point(c.x - 0, c.y + 1),
                new Point(c.x + 1, c.y + 1)
        );
    }

    @Override
    public String toString() {
        return Grid.print(10, 10, (x, y) -> isAlive(x, y) ? "X" : "_");
    }

    private boolean isAlive(Integer x, Integer y) {
        return board.contains(new Point(x, y));
    }
}
