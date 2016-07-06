import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dawid Janik
   todo throws Illegal
 */
public class Field implements  MineSweeper {


    private Cell[][] area;

    private Integer n; // n - lines of field
    private Integer m; // m - characters per one-n-line

//===================================================================
//Interface @Override

    /**
     *
     * @param mineField string containing the mines
     * @throws IllegalArgumentException
     */
    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {


        String[] lines = StringUtils.split(mineField, System.lineSeparator()); // Split Input string at \n character
        n = lines.length;       //set the height
        m = lines[0].length();  //set the width

        System.out.println("n x m: " + n + " x " + m ); //Show size of Field

        this.area = new Cell[n][m]; //  Create game area[n x m] with Cell's

        // Fill game area with proper game value taken from input String
        for(int i=0; i<n;i++){ //height
            for (int j=0; j<m;j++){ //width

                area[i][j] = new Cell();

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
     *
     * @return
     * @throws IllegalStateException
     */
    @Override
    public String getHintField() throws IllegalStateException {

        for(int i=0; i<n; i++){
          for(int j=0; j<m; j++){
             List<Cell> l = getNeighbors(i,j); //Get neighbors surrounding current Cell
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
     *
     * @param i
     * @param j
     * @return
     */
    public List<Cell> getNeighbors(int i, int j) {

        System.out.println("I=" + i + " J=" + j);
        List<Cell> neighbors = new ArrayList<Cell>();

        if (this.area[i][j].isMine){
            System.out.println("Left Cell with Mine " + "I=" + i + " J=" + j); // Mine cell return 0-elements list
        }
        if (!this.area[i][j].isMine) { // If not mine check neighbors

            //Corresponding schema to take all 8 neighbors
            int left_x = j - 1;
            int right_x = j + 1;
            int top_y = i - 1;
            int bottom_y = i + 1;


            /**
             * Each cell has 8 neighbors on game area.
             * If the cell is on the edge (e.g [0,0]), and want take neighbor on the illegal position
             * Throw Exception, replace this neighbor with new Cell (not-mine).
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
//                            System.out.println("Cell 0");
                            neighbors.add(new Cell());
                         }
                        break;
                    case 1:
                        try{
                        neighbors.add(new Cell(this.area[top_y][j]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 1");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 2:
                        try{
                        neighbors.add(new Cell(this.area[top_y][right_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 2");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 3:
                        try{
                        neighbors.add(new Cell(this.area[i][right_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 3");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 4:
                        try{
                        neighbors.add(new Cell(this.area[bottom_y][right_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 4");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 5:
                        try{
                        neighbors.add(new Cell(this.area[bottom_y][j]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 5");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 6:
                        try{
                        neighbors.add(new Cell(this.area[bottom_y][left_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 6");
                            neighbors.add(new Cell());
                        }
                        break;
                    case 7:
                        try{
                        neighbors.add(new Cell(this.area[i][left_x]));
                        }catch(ArrayIndexOutOfBoundsException e){
//                            System.out.println("Cell 7");
                            neighbors.add(new Cell());
                        }
                        break;
                }//switch

            } // for


        }// if
            return (neighbors.size() > 8) ? null : neighbors; // If something go very wrong in size's return null
    }

//===================================================================
//Methods()


  @Override
  public String toString() {
            return "Field{" +
                    "area=" + Arrays.deepToString(area) +
//                ", mineField='" + mineField + '\'' +
//                ", n=" + n +
//                ", m=" + m +
                    '}';
        }


    /**
     *
     */
    public void showField() {
        System.out.println("Length "+ area.length);
        for(int i=0; i<n;i++){
            for (int j=0; j<m;j++){

                System.out.print(" " + area[i][j].value);
//                System.out.print(" " + area[i][j].isMine);
//
                }
            System.out.println();
            }

    }

    /**
     *
     */
    public void showHint() {
        System.out.println("Length " + area.length);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (this.area[i][j].isMine) {
                    System.out.print(" " + area[i][j].value);
                } else
                    System.out.print(" " + area[i][j].hintValue);


            }
            System.out.println();
        }
    }

    /**
     *
     * @return
     */
    public String showOutput() {
        System.out.println("Length " + area.length);
        StringBuffer output = new StringBuffer((n * m) + n +12); // + 10
        output.append("Hint-field:");

           // output.append(i);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (this.area[i][j].isMine) {
                    output.append("*");
                }else{
                    output.append(area[i][j].hintValue);
                }
            }
            if (i < n-1){
                output.append("\\n");// escape with \
            }

        }
           return output.toString();
    }

}// End of Field
