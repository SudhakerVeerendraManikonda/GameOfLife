package assign1;

public class GameOfLife
{
    public boolean isCellAliveForNextGeneration(int aliveNeighborsCount, boolean isAlive)
    {
        return aliveNeighborsCount == 3 || isAlive && aliveNeighborsCount == 2;
    }

    public int countAliveNeighbors(boolean[][] cells, int row, int column)
    {
        int neighbors = 0;

        for (int rowIndex = row - 1 ; rowIndex <= row + 1 ; rowIndex++)
            for (int columnIndex = column - 1 ; columnIndex <= column + 1 ; columnIndex++)
                if(isCellAlive(rowIndex, columnIndex, cells))
                    neighbors++ ;

        return isCellAlive(row, column, cells)  ? neighbors - 1 : neighbors;
    }

    public boolean isCellAlive(int rowIndex, int columnIndex, boolean[][] cells)
    {
        return rowIndex >= 0  && rowIndex < cells.length && columnIndex >= 0 && columnIndex < cells[0].length && cells[rowIndex][columnIndex];
    }

    public boolean [][] generateNextPattern(boolean [][] cells)
    {
        boolean[][] nextGenerationCells = new boolean [ cells.length ][ cells[0].length ];

        for (int row = 0 ; row < cells.length ; row++)
            for (int column = 0 ; column < cells[0].length ; column++)
                nextGenerationCells[row][column] = isCellAliveForNextGeneration(countAliveNeighbors(cells, row, column), cells[row][column] );

        return nextGenerationCells;
    }
}
