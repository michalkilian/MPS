package org.example.view;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.example.logic.Junction;
import org.example.logic.cars.Vehicle;

public class JunctionGrid {
    private Cell[][] cellMatrix;
    private int x;
    private int y;

    public JunctionGrid(int width, int height) {
        this.x = 2*height+width;
        this.y = 2*height+width;
        initializeCellMatrix(x, y);

    }

    public void setCellMatrixColor(int x, int y, Color color) {
        cellMatrix[x][y] = new Cell(color, x, y);
    }

    public void setCellMatrixVehicleToObserve(int x, int y, Vehicle vehicle) {
        cellMatrix[x][y].setVehicle(vehicle);
    }


    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public void initializeCellMatrix(int x, int y) {
        Cell[][] cellsCreator = new Cell[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cellsCreator[i][j] = new Cell(Color.BLACK, i, j);
            }
        }

        this.cellMatrix = cellsCreator;
    }

    public Parent updateColors(Junction junction) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                try{
                    int cell = junction.getGrid().get(i,j);
                    if(cell == 0){
                        setCellMatrixColor(i, j, Color.BLACK);
                    }
                    else if(cell == 1){
                        setCellMatrixColor(i, j, Color.RED);
                    }
                }catch (IndexOutOfBoundsException e){
                    setCellMatrixColor(i, j, Color.GREEN);
                }

            }
        }
        return getFxGrid();
    }

    public Parent getFxGrid(){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(x*3, y*3);
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                gridPane.add(getCellMatrix()[i][j], i, j);
            }
        }
        return gridPane;
    }
}
