import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dawid Janik

 */

public class Field implements  MineSweeper {

    private static Field instance = null;


    public Cell[][] area; // [] ??
    private String mineField = "*...\n..*.\n....";

     Integer n; // n - lines of field
    private Integer m; // m - characters per one-n-line

//=================================================================================================================
//Metody Singleton

    public static Field getInstace(){

        if (instance==null){
            instance = new Field();
        }

        return instance;
    }



//===================================================================
//Interface @Override

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {

       // System.out.println(mineField);
        //System.out.println();

        String[] lines = StringUtils.split(mineField, System.lineSeparator()); // Check with MacOS
        n = lines.length;
        m = lines[0].length();

        System.out.println("n x m: " + n + " x " + m ); //lines[0]


        this.area = new Cell[n][m]; //  Mam tablice komorek n x m

       // System.out.println(area.toString()); //area.toString()
        //System.out.println(area[0][0].toString());

        // Chce to zapelnic
        for(int i=0; i<n;i++){
            for (int j=0; j<m;j++){

                area[i][j] = new Cell();
                //System.out.println(String.valueOf(lines[i].charAt(j)));

                if(String.valueOf(lines[i].charAt(j)).equals("*")){
                    area[i][j].isMine = true; // np. * . . . \n
                    area[i][j].value = "*";
                }else{
                    area[i][j].isMine = false;
                    area[i][j].value = ".";
                }

            }
                //System.out.println("////////");
        }

        //showField();

    }



    @Override
    public String getHintField() throws IllegalStateException {

        for(int i=0; i<n;i++){
          for(int j=0; j<m;j++){
              getNeighbors(i,j);
            }
        }

        return "";
    }
    public List<Cell> getNeighbors(int i, int j) { // i-ty wiersz j-ta kolomna

        System.out.println("I=" + i + " J=" + j);
        List<Cell> neighbors = new ArrayList<Cell>();


        if (!this.area[i][j].isMine) { // jesli nie jest mina ta sprawdz sasiadow

            int left_x = j - 1;
            int right_x = j + 1;
            int top_y = i - 1;
            int bottom_y = i + 1;

            if (left_x < 0);
                left_x = 0;

            if (right_x > m - 1)
                right_x = m - 1;

            if (top_y < 0)
                top_y = 0;

            if (bottom_y > n - 1)
                bottom_y = n - 1;

/**
 * 0 - lewa gorna; 1 - centralna gorna; 2 - prawa gorna; 3 - prawa
 * srodkowa 4 - prawa dolna; 5 - centralna dolna; 6 - lewa dolna; 7 -
 * lewa srodkowa
 */
            for (int ii = 0; ii < 8; ii++) {
                switch (ii) {
                    case 0:
                       // System.out.println(left_x);
                        try{
                        }catch(ArrayIndexOutOfBoundsException e){

                         }
                            neighbors.add(new Cell(this.area[top_y][left_x])); //n-1 , m-1
                        break;
                    case 1:
                        neighbors.add(new Cell(this.area[top_y][i]));
                        break;
                    case 2:
                        neighbors.add(new Cell(this.area[top_y][right_x]));
                        break;
                    case 3:
                        System.out.println(j + " " + right_x);
                        neighbors.add(new Cell(this.area[i][right_x]));
                        break;
                    case 4:
                        neighbors.add(new Cell(this.area[bottom_y][right_x]));
                        break;
                    case 5:
                        neighbors.add(new Cell(this.area[bottom_y][i]));
                        break;
                    case 6:
                        neighbors.add(new Cell(this.area[bottom_y][left_x]));
                        break;
                    case 7:
                        neighbors.add(new Cell(this.area[i][left_x]));
                        break;
                }//switch

            } // for


        }// if
            return neighbors;
    }

//===================================================================
//Methods()


//  @Override
//  public String toString() {
//            return "Field{" +
//                    "area=" + Arrays.deepToString(area) +
////                ", mineField='" + mineField + '\'' +
////                ", n=" + n +
////                ", m=" + m +
//                    '}';
//        }


    public void showField() {
        System.out.println("Length "+ area.length);
        for(int i=0; i<n;i++){
            for (int j=0; j<m;j++){

                System.out.print(" " + area[i][j].value);
                //System.out.print(" " + area[i][j].isMine);

                }
            System.out.println();
            }

    }



}// End of Field
