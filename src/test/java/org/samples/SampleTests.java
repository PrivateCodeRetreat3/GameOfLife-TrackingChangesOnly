package org.samples;


import org.approvaltests.Approvals;
import org.approvaltests.StoryBoard;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class SampleTests {
    @Test
    public void testSingleCellAlive() {
        StoryBoard b = new StoryBoard();
        GameOfLife g = new GameOfLife(new Point(1, 1));
        b.add(g.toString());
        g.advance();
        b.add(g.toString());
        Approvals.verify(b);
    }

    @Test
    public void testGlider() {
        StoryBoard b = new StoryBoard();
        GameOfLife g = new GameOfLife(new Point(1, 1),
                 new Point(2, 2),
                 new Point(3, 0),
                 new Point(3, 1),
                 new Point(3, 2)
        );
        b.add(g.toString());
        for (int i = 0; i < 4; i++) {
            g.advance();
            b.add(g.toString());
        }
        Approvals.verify(b);
    }
}
