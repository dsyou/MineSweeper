import java.util.ArrayList;
import java.util.List;

/**
 * @author  Dawid Janik
 *
 */
public class Neighbours {

    private Cell[][] area  ;
    Field f = Field.getInstace();

    private int widht = 0; //Field.getInstace().area[0].length ; // n
    private int height =0; // Field.getInstace().area.length;    //m TODO do zmiany to

//===================================================================
//Constructor

    public Neighbours() {
        super();
    }


//===================================================================
//Methods()

    public List<Cell> getNeighbors(int x, int y) {

         System.out.println("X="+x+" Y="+y);
         List<Cell> neighbors = new ArrayList<Cell>();


        widht = area[0].length;
        height = area.length;

        int left_x = x - 1;
        int right_x = x + 1;
        int top_y = y - 1;
        int bottom_y = y + 1;

            if (left_x < 0)
                left_x = 0;

            if (right_x > widht - 1)
                right_x = widht - 1;

            if (top_y < 0)
                top_y = 0;

            if (bottom_y > height - 1)
                bottom_y = height - 1;

//        // na wszelki wypadek sprawdz poprawnosc współrzędnych sąsiadów
//        if (left_x < 0)
//            left_x = 0;
//        if (right_x > widht)
//            right_x = widht;
//        if (top_y < 0)
//            top_y = 0;
//        if (bottom_y > height)
//            bottom_y = height;


        /**
         * 0 - lewa gorna; 1 - centralna gorna; 2 - prawa gorna; 3 - prawa
         * srodkowa 4 - prawa dolna; 5 - centralna dolna; 6 - lewa dolna; 7 -
         * lewa srodkowa
         */
            for (int i = 0; i < 8; i++) {
                switch (i) {
                    case 0:
                            neighbors.add(new Cell(this.area[left_x][top_y]));
                        break;
                    case 1:
                            neighbors.add(new Cell(this.area[x][top_y]));
                        break;
                    case 2:
                            neighbors.add(new Cell(this.area[right_x][top_y]));
                        break;
                    case 3:
                            neighbors.add(new Cell(this.area[right_x][y]));
                        break;
                    case 4:
                            neighbors.add(new Cell(this.area[right_x][bottom_y]));
                        break;
                    case 5:
                            neighbors.add(new Cell(this.area[x][bottom_y]));
                        break;
                    case 6:
                            neighbors.add(new Cell(this.area[left_x][bottom_y]));
                        break;
                    case 7:
                            neighbors.add(new Cell(this.area[left_x][y]));
                        break;
                }//switch
            }//for
//                    case 0:
//                        if (this.area[left_x][top_y].phase == c.phase  ){
//                            Cell z=new Cell (this.area[left_x][top_y]);
//                            z.Cx=left_x;
//                            z.Cy=top_y;
//                            //	neighbors.add(new Cell(this.area[left_x][top_y]));
//                            neighbors.add(z);
//
//                        }



        return neighbors;
        }

}
