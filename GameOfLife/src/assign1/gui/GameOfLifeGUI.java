package assign1.gui;

import assign1.GameOfLife;
import java.util.Arrays;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;


public class GameOfLifeGUI extends Application
{
    final int gridSize = 5;
    static String selectedPattern = "";
    static String currentPattern = "";
    GameOfLife _gameOfLife;

    private Button[][] buttonArray;
    boolean [][] cells = new boolean[gridSize][gridSize];

    GridPane boardGrid = new GridPane();
    final ComboBox<String> patternComboBox = new ComboBox<String>();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        _gameOfLife = new GameOfLife();
        buttonArray = new Button[gridSize][gridSize];

        boardGrid.setStyle("-fx-background-color: #C0C0C0;");

        for(int row = 0; row < gridSize; row++)
            for(int column = 0; column < gridSize; column++)
                initializeButton(row, column, boardGrid);

        initializeComboBox();
        game(boardGrid);
        initializeScene(primaryStage, boardGrid);
    }

    private void initializeComboBox()
    {
        patternComboBox.getItems().addAll(
                "Blinker",
                "Glider",
                "Block",
                "Beehive",
                "Toad",
                "Beacon"
        );

        boardGrid.add(patternComboBox, 7 , 0);
        patternComboBox.setValue("-SELECT-");
        patternComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue, String o, String changedOption) {

                selectedPattern = changedOption;
                setGridToInitialState();

                cells = setInitialPattern(changedOption);
                setPatternOnGrid(cells);
            }
        });
    }

    private void initializeScene(Stage primaryStage, GridPane boardGrid)
    {
        Scene scene = new Scene(boardGrid, boardGrid.getMaxWidth(),boardGrid.getMaxHeight() , Color.DIMGREY);
        primaryStage.setTitle("GameOfLife");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeButton(int row, int column, GridPane grid)
    {
        buttonArray[row][column] = new Button();
        buttonArray[row][column].autosize();
        buttonArray[row][column].setFocusTraversable(false);
        buttonArray[row][column].setMinSize(30, 30);
        buttonArray[row][column].setDisable(true);
        buttonArray[row][column].setStyle("-fx-base: Black;");

        grid.add(buttonArray[row][column], column, row);
    }

    public boolean[][] turnCellsAlive(int[][] neighbors)
    {
        for(int position = 0; position < neighbors.length; position++)
            cells[ neighbors[position][0]] [neighbors[position][1] ] = true;

        return cells;
    }

    public void setPatternOnGrid( boolean [][] cellArray )
    {
        for(int row = 0; row<gridSize; row++)
            for(int column = 0; column<gridSize; column++)
                if (cellArray[row][column])
                    buttonArray[row][column].setStyle("-fx-base: Red;");
                else
                    buttonArray[row][column].setStyle("-fx-base: Black;");
    }

    private void game(GridPane boardGrid)
    {
        Button startButton = new Button("START");
        startButton.setTextFill(Color.BLACK);
        startButton.setFont(new Font("Lucidia Sans Unicode", 10));
        boardGrid.add(startButton, 7, 8);

        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {
                try {
                    onClick(event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Button endButton = new Button("STOP");
        endButton.setTextFill(Color.BLACK);
        endButton.setFont(new Font("Lucidia Sans Unicode", 10));
        boardGrid.add(endButton, 7, 9);

        endButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {
                selectedPattern = "";
            }
        });
    }

    private void onClick(MouseEvent event) throws InterruptedException
    {
        boolean nextPattern [][] = new boolean[5][5];

        if(event.getButton() == MouseButton.PRIMARY)
        {
            nextPattern = _gameOfLife.generateNextPattern(cells);
            currentPattern = selectedPattern;
            if (hasGameProgressed(cells, nextPattern, selectedPattern))
                return;
            return;
        }
    }

    public boolean hasGameProgressed(boolean[][] cellArray, final boolean[][] nextPattern, String patternName)
    {
        if(currentPattern.equals(patternName))
            if (!( Arrays.deepEquals(cellArray, nextPattern)))
            {
                setPatternOnGrid(nextPattern);

                PauseTransition pauseTransition = new PauseTransition();
                pauseTransition.setDuration(Duration.seconds(1));
                pauseTransition.play();
                pauseTransition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent)
                    {
                        boolean[][] pattern = _gameOfLife.generateNextPattern(nextPattern);
                        hasGameProgressed(nextPattern, pattern, selectedPattern);
                    }
                });
            }
        return false;
    }

    public boolean[][] setInitialPattern(String patternName)
    {
        boolean initialPattern[][] = new boolean[5][5];

        if(patternName.equalsIgnoreCase("Blinker"))

            initialPattern = turnCellsAlive(new int[][]{ {2,3} , {2,1} , {2,2} });

        else if(patternName.equalsIgnoreCase("Glider"))

            initialPattern =  turnCellsAlive(new int[][]{ {0,2} , {1,3} , {2,1} , {2,2} , {2,3} });

        else if(patternName.equalsIgnoreCase("Block"))

            initialPattern = turnCellsAlive(new int[][]{ {2,3} , {1,2} , {2,2} , {1,3} });

        else if(patternName.equalsIgnoreCase("Beehive"))

            initialPattern = turnCellsAlive(new int[][]{ {2,1} , {1,2} , {2,4} , {1,3} , {3,2} , {3,3} });

        else if(patternName.equalsIgnoreCase("Toad"))

            initialPattern = turnCellsAlive(new int[][]{ {2,3} , {2,4} , {2,2} , {3,1} , {3,2} , {3,3} });

        else if(patternName.equalsIgnoreCase("Beacon"))

            initialPattern = turnCellsAlive(new int[][]{ {1,1} , {2,1} , {1,2} , {2,2} , {3,3} , {3,4} , {4,3} , {4,4} });

        return initialPattern;
    }

    public void setGridToInitialState()
    {
        for (int row = 0; row < cells.length; row++)
            for (int column = 0; column < cells[0].length; column++)
            {
                cells[row][column] = false;
                buttonArray[row][column].setStyle("-fx-base: Black;");
            }
    }
}
