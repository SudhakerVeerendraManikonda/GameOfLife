import assign1.GameOfLife;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class GameOfLifeTest extends TestCase
{
    GameOfLife _gameOfLife;
    boolean LIVE = true;
    boolean DEAD = false;

    public void setUp()
    {
        _gameOfLife = new GameOfLife();
    }

    public void testCanary()
    {
        assertTrue(true);
    }

    public void testDeadCellWithZeroNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(0, DEAD));
    }

    public void testDeadCellWithOneNeighborIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(1, DEAD));
    }

    public void testDeadCellWithTwoNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(2, DEAD));
    }

    public void testDeadCellWithThreeNeighborsIsAlive()
    {
        assertEquals(LIVE, _gameOfLife.isCellAliveForNextGeneration(3, DEAD));
    }

    public void testDeadCellWithFourNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(4, DEAD));
    }

    public void testDeadCellWithEightNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(8, DEAD));
    }

    public void testLiveCellWithZeroNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(0, LIVE));
    }

    public void testLiveCellWithOneNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(1, LIVE));
    }

    public void testLiveCellWithTwoNeighborsIsAlive()
    {
        assertEquals(LIVE, _gameOfLife.isCellAliveForNextGeneration(2, LIVE));
    }

    public void testLiveCellWithThreeNeighborsIsAlive()
    {
        assertEquals(LIVE, _gameOfLife.isCellAliveForNextGeneration(3, LIVE));
    }

    public void testLiveCellWithFourNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(4, LIVE));
    }

    public void testLiveCellWithEightNeighborsIsDead()
    {
        assertEquals(DEAD, _gameOfLife.isCellAliveForNextGeneration(8, LIVE));
    }

    public void testNeighbourCountShouldBeZeroWithoutNeighbours()
    {
        boolean[][] deadGrid = new boolean[5][5];
        assertEquals(0, _gameOfLife.countAliveNeighbors(deadGrid, 4, 4));
    }

    public boolean[][] turnCellsAlive(int[][] neighbors)
    {
        boolean [][]cells = new boolean[5][5];
        for(int position = 0; position < neighbors.length; position++)
        {
            cells[ neighbors[position][0]] [neighbors[position][1] ] = true;
        }
        return cells;
    }

    public void testLiveNeighborCountForCellAtCenterWithOneNeighbor()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,1} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForCellAtCenterWithTwoNeighbors()
    {
        boolean cells[][]= turnCellsAlive(new int[][]{ {1,2} , {2,3} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForCellAtCenterWithThreeNeighbors()
    {
        boolean cells[][]= turnCellsAlive(new int[][]{ {1,1} , {1,2} , {2,3} });
        assertEquals(3, _gameOfLife.countAliveNeighbors(cells , 2, 2));
    }

    public void testLiveNeighborCountForCellAtCenterWithFourNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {3,2} , {1,2} , {1,3} , {2,3} });
        assertEquals(4, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForCellAtCenterWithEightNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {1,1} , {1,2} , {1,3} , {2,1} , {3,1} , {3,2} , {3,3} , {2,3} });
        assertEquals(8, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForCellAtCenterWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,3}, {2,3} , {4,4} , {2,0} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForLiveCellAtCenterWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,1}, {2,2}, {1,1} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForLiveCellAtCenterWithEightNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,2} , {1,1} , {1,2} , {1,3} , {2,1} , {3,1} , {3,2} , {3,3} , {2,3} });
        assertEquals(8, _gameOfLife.countAliveNeighbors(cells, 2, 2));
    }

    public void testLiveNeighborCountForCellAtTopLeftEdgeWithThreeNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {1,1} , {1,0} , {0,1} });
        assertEquals(3, _gameOfLife.countAliveNeighbors(cells, 0, 0));
    }

    public void testLiveNeighborCountForLiveCellAtTopLeftEdgeWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,0} , {0,1} , {1,0} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 0, 0));
    }

    public void testLiveNeighborCountForLiveCellAtTopLeftEdgeWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,0} , {0,1} , {1,3} , {2,1} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 0, 0));
    }

    public void testLiveNeighborCountForCellAtTopRightEdgeWithThreeNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,3} , {1,3} , {1,4} });
        assertEquals(3, _gameOfLife.countAliveNeighbors(cells, 0, 4));
    }

    public void testLiveNeighborCountForLiveCellAtTopRightEdgeWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,4} , {0,3} , {1,4} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 0, 4));
    }

    public void testLiveNeighborCountForLiveCellAtTopRightEdgeWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,4} , {0,3} , {3,4} , {2,3} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 0, 4));
    }

    public void testLiveNeighborCountForCellAtBottomLeftEdgeWithThreeNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {3,0} , {3,1} , {4,1} });
        assertEquals(3, _gameOfLife.countAliveNeighbors(cells, 4, 0));
    }

    public void testLiveNeighborCountForLiveCellAtBottomLeftEdgeWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,0} , {3,1} , {4,1} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 4, 0));
    }

    public void testLiveNeighborCountForLiveCellAtBottomLeftEdgeWithZeroNeighborsAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,0} , {1,0} , {4,2} });
        assertEquals(0, _gameOfLife.countAliveNeighbors(cells, 4, 0));
    }

    public void testLiveNeighborCountForCellAtBottomRightEdgeWithThreeNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {3,3} , {3,4} , {4,3} });
        assertEquals(3, _gameOfLife.countAliveNeighbors(cells, 4, 4));
    }

    public void testLiveNeighborCountForLiveCellAtBottomRightEdgeWithOneNeighbor()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,4}  , {3,4} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 4, 4));
    }

    public void testLiveNeighborCountForLiveCellAtBottomRightEdgeWithZeroNeighborsAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,4} , {2,3} , {1,2} });
        assertEquals(0, _gameOfLife.countAliveNeighbors(cells, 4, 4));
    }

    public void testLiveNeighborCountForCellAtCenterOnFirstRowWithFiveNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,1} , {1,1} , {1,2} , {1,3} , {0,3} });
        assertEquals(5, _gameOfLife.countAliveNeighbors(cells, 0, 2));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnFirstRowWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,2} , {1,1} , {0,3} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 0, 2));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnFirstRowWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {0,2} , {0,3} , {1,4} , {2,4} , {3,2} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 0, 2));
    }

    public void testLiveNeighborCountForCellAtCenterOnFirstColumnWithFiveNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {3,0} , {1,0} , {3,1} , {1,1} ,  {2,1} });
        assertEquals(5, _gameOfLife.countAliveNeighbors(cells, 2, 0));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnFirstColumnWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,0} , {3,1} , {2,1} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 2, 0));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnFirstColumnWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,0} , {3,1} , {4,2} , {3,4} , {0,0} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 2, 0));
    }

    public void testLiveNeighborCountForCellAtCenterOnLastRowWithFiveNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {3,1} , {3,2} , {3,3} , {4,1} , {4,3} });
        assertEquals(5, _gameOfLife.countAliveNeighbors(cells, 4, 2));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnLastRowWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,2} , {3,2} , {4,3} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 4, 2));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnLastRowWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {4,2} , {3,3} , {0,0} , {2,3} , {1,4} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 4, 2));
    }

    public void testLiveNeighborCountForCellAtCenterOnLastColumnWithFiveNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {1,3} , {2,3} , {3,3} , {1,4} , {3,4} });
        assertEquals(5, _gameOfLife.countAliveNeighbors(cells, 2, 4));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnLastColumnWithTwoNeighbors()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,4} , {1,3} , {3,4} });
        assertEquals(2, _gameOfLife.countAliveNeighbors(cells, 2, 4));
    }

    public void testLiveNeighborCountForLiveCellAtCenterOnLastColumnWithOneNeighborAndOtherCellsAlive()
    {
        boolean cells[][] = turnCellsAlive(new int[][]{ {2,4} , {3,3} , {1,2} , {2,0} , {3,1} });
        assertEquals(1, _gameOfLife.countAliveNeighbors(cells, 2, 4));
    }

    public void testGenerateNextPatternForBlockPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {2,3} , {1,2} , {2,2} , {1,3} });
        assertArrayEquals(cells, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternForBeehivePattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {2,1} , {1,2} , {2,4} , {1,3} , {3,2} , {3,3} });
        assertArrayEquals(cells, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternForBoatPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {1,1} , {1,2} , {2,1} , {2,3} , {3,2} });
        assertArrayEquals(cells, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternForLoafPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {1,2} , {1,3} , {2,1} , {2,4} , {3,2} , {3,4} , {4,3} });
        assertArrayEquals(cells, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternForBlinkerPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {2,3} , {2,1} , {2,2} });
        boolean expectedNextPattern [][] = turnCellsAlive(new int[][] { {2,2} , {3,2} , {1,2} });
        assertArrayEquals(expectedNextPattern, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternForBlinkerPatternN()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {2,3} , {2,1} , {2,2} });
        boolean expectedNextPattern [][] = turnCellsAlive(new int[][] { {2,3} , {2,2} , {2,1} });

        assertArrayEquals(expectedNextPattern, _gameOfLife.generateNextPattern(_gameOfLife.generateNextPattern(_gameOfLife.generateNextPattern(_gameOfLife.generateNextPattern(cells)))));
    }

    public void testGenerateNextPatternForToadPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {2,3} , {2,4} , {2,2} , {3,1} , {3,2} , {3,3} });
        boolean expectedNextPattern [][] = turnCellsAlive(new int[][] { {1,3} , {2,1} , {2,4} , {3,1} , {3,4} ,{4,2} });
        assertArrayEquals(expectedNextPattern, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternBeaconPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {1,1} , {2,1} , {1,2} , {2,2} , {3,3} , {3,4} , {4,3} , {4,4} });
        boolean expectedNextPattern [][] = turnCellsAlive(new int[][] { {1,1} , {2,1} , {1,2} , {3,4} , {4,3} , {4,4} });
        assertArrayEquals(expectedNextPattern, _gameOfLife.generateNextPattern(cells));
    }

    public void testGenerateNextPatternForGliderPattern()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {0,2} , {1,3} , {2,1} , {2,2} , {2,3} });
        boolean expectedNextPattern [][] = turnCellsAlive(new int[][] { {1,1} , {2,2} , {1,3} , {3,2} , {2,3} });
        assertArrayEquals(expectedNextPattern, _gameOfLife.generateNextPattern(cells));
    }

    public void testGeneratePatternThatWillDieInOneGeneration()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {2,3} , {2,2} });
        boolean nextGenerationPattern [][] = _gameOfLife.generateNextPattern(cells);
        boolean[][] deadGrid = new boolean[5][5];

        assertArrayEquals(deadGrid, nextGenerationPattern);
    }

    public void testGeneratePatternThatWillDieInTwoGenerations()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {0,1} , {0,2} , {0,3} });
        boolean[][] deadGrid = new boolean[5][5];
        assertArrayEquals(deadGrid, _gameOfLife.generateNextPattern(_gameOfLife.generateNextPattern(cells)));
    }

    public void testGeneratePatternThatWillReachBoundaryInOneGeneration()
    {
        boolean cells [][] = turnCellsAlive(new int[][]{ {0,1} , {0,2} , {0,3} , {1,2} ,{2,2} });
        boolean[][] expectedNextPattern = turnCellsAlive(new int[][]{ {0,1} , {0,2} , {0,3} });
        assertArrayEquals(expectedNextPattern, _gameOfLife.generateNextPattern(cells));
    }
    
    public void testGenerateNextPatternWithDeadGrid()
    {
        boolean [][] deadGrid = new boolean[3][3];
        assertArrayEquals(deadGrid, _gameOfLife.generateNextPattern(deadGrid));
    }
}

