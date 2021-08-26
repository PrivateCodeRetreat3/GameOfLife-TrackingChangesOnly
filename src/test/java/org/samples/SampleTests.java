package org.samples;


import org.approvaltests.Approvals;
import org.approvaltests.StoryBoard;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTests
{
  @Test
  public void testSingleCellAlive()
  {
    StoryBoard b = new StoryBoard();
    GameOfLife g = new GameOfLife(new Point(1,1));
    b.add(g.toString());
    g.advance();
    b.add(g.toString());
    Approvals.verify(b);
  }
}
