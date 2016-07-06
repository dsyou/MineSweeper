/**
*@author Dawid Janik
 *
 *
*/
public class Main {



    public static void main(String[] args) {

        System.out.println("Running App\n");

        Field a = new Field();

        a.setMineField("*...\n..*.\n....");
        a.showField();

        a.getHintField();

        //System.out.println(a.toString());

    }




}
