package pl.minesweeper.core;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dawid Janik
 * @since 20.03.2018
 */
public class MineSweeperImpl implements MineSweeper {
    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {

    }

    @Override
    public String getHintField() throws IllegalStateException {
        return null;
    }

    /**
     * Implementation of interface method used to generate game pl.minesweeper.area/field
     * @param mineField string containing the mines
     * @throws IllegalArgumentException if mineField is not properly formatted
     */
    @Override
    public void setMineField(String mineField) throws IllegalStateException {


        String[] lines = StringUtils.split(mineField, System.lineSeparator()); // Split Input string at \n character
        n = lines.length;       //set the height
        m = lines[0].length();  //set the width

        checkMineFieldStructure(lines);
        System.out.println("n x m: " + n + " x " + m ); //Show size of pl.minesweeper.area.Field

        this.area = new Cell[n][m]; //  Create game pl.minesweeper.area[n x m] with pl.minesweeper.area.Cell's

        // Fill game pl.minesweeper.area with proper game value taken from input String
        for(int i=0; i<n;i++){ //height
            for (int j=0; j<m;j++){ //width

                area[i][j] = new Cell();//initialization of the current field point with new Cell()

                if(String.valueOf(lines[i].charAt(j)).equals("*")) {
                    area[i][j].isMine = true; // e.g. * . . . \n
                    area[i][j].value = "*";
                }
                if(String.valueOf(lines[i].charAt(j)).equals(".")) {
                    area[i][j].isMine = false;
                    area[i][j].value = ".";
                }

            }

        }

    }

    /**
     * This method is checking properly formatted mineField input.
     * Accepted input must have only two characters:
     * "*" - mine field
     * "." - not mine field
     * If any else occur throw exception.
     * However this method also check that following input is empty,
     * or short/to long.
     *
     * @exception IllegalArgumentException with proper Message
     * @param lines array divided mineField input string
     */
    private void checkMineFieldStructure(String[] lines) {

        if(lines.length == 0){
            throw  new IllegalArgumentException("Input cannot be empty");
        }

        for (int i=0; i<lines.length; i++){
            if(lines[i].length() != m){
                throw  new IllegalArgumentException("Input characters size is not proper");
            }
        }
        for(int i=0; i<lines.length; i++) {
            if ( !StringUtils.containsOnly(lines[i], '*','.') ){
                throw new IllegalArgumentException("Invalid value in input string");
            }
        }
    }


    /**
     * Implementation of method taken form pl.minesweeper.core.MineSweeper interface
     *
     * @return a string representation of the hint-field
     * @throws IllegalStateException if the mine-field has not been initialised (i.e. setMineField() has not been called)
     */
    @Override
    public String getHintField() throws IllegalStateException {

        if((area == null)){
            throw new IllegalArgumentException("Method setMineFiled() has not been called");
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                List<Cell> l = getNeighbors(i,j); //Get neighbors surrounding current pl.minesweeper.area.Cell
                //System.out.println("L.size()"+l.size()); //Show me list.size() of incoming neighbors
                if (l == null){
                    System.err.println("Error Null Neighbors list"); // This situation should never happen
                    System.exit(-1); // Exit immediately
                }

                if (l.size() != 0){ //Check list size: 0-mines, 8-no mine cell
                    for(int n=0; n<8; n++){

                        if(l.get(n).isMine){ // If the neighbor is Mine increment current cell.hintValue
                            //System.out.println("Add +1");
                            this.area[i][j].hintValue = this.area[i][j].hintValue +1;//++ ;
                        }

                    }
                }

            }//j
        }//i

        return showOutput();
    }

    /**
     * This method is used to get 8 neighbors of current cell.
     * The return value is a list of neighbors.
     * It is Important to check size of list- mine_Cell is skipped.
     *
     * @param i current row value
     * @param j current column value
     * @return list of neighbors
     */
    public List<Cell> getNeighbors(int i, int j) {

        System.out.println("I=" + i + " J=" + j);
        List<Cell> neighbors = new ArrayList<Cell>();

        if (this.area[i][j].isMine){
            System.out.println("Left pl.minesweeper.area.Cell with Mine " + "I=" + i + " J=" + j); // Mine cell return 0-elements list
        }
        if (!this.area[i][j].isMine) { // If not mine check neighbors

            //Corresponding schema to take all 8 neighbors
            int left_x = j - 1;
            int right_x = j + 1;
            int top_y = i - 1;
            int bottom_y = i + 1;


            /**
             * Each cell has 8 neighbors on game pl.minesweeper.area.
             * If the cell is on the edge (e.g [0,0]), and want take neighbor on the illegal position
             * Throw Exception, replace this neighbor with new pl.minesweeper.area.Cell (not-mine).
             * Non-periodic bound condition
             * Position are:
             * 0 - left top; 1- central top; 2- right top; 3-right center;
             * 4- right bottom; 5-central bottom; 6-left bottom; 7-left bottom
             *
             * In polish:
             * 0 - lewa gorna; 1 - centralna gorna; 2 - prawa gorna; 3 - prawa
             * srodkowa 4 - prawa dolna; 5 - centralna dolna; 6 - lewa dolna; 7 -
             * lewa srodkowa
             */
            for (int ii = 0; ii < 8; ii++) {
                switch (ii) {
                    case 0:
                        try{
                            neighbors.add(new Cell(this.area[top_y][left_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 0");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 1:
                        try{
                            neighbors.add(new Cell(this.area[top_y][j]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 1");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 2:
                        try{
                            neighbors.add(new Cell(this.area[top_y][right_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 2");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 3:
                        try{
                            neighbors.add(new Cell(this.area[i][right_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 3");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 4:
                        try{
                            neighbors.add(new Cell(this.area[bottom_y][right_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 4");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 5:
                        try{
                            neighbors.add(new Cell(this.area[bottom_y][j]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 5");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 6:
                        try{
                            neighbors.add(new Cell(this.area[bottom_y][left_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 6");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 7:
                        try{
                            neighbors.add(new Cell(this.area[i][left_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("pl.minesweeper.area.Cell 7");
                            neighbors.add(new Cell());
                        }
                        break;
                }//switch

            } // for


        }// if
        return (neighbors.size() > 8) ? null : neighbors; // If something go very wrong in size's return null
    }

}
