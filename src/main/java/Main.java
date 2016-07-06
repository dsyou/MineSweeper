/**
*@author Dawid Janik
 *
 *
*/
public class Main {


    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Running App MineSweeper\n");

        Field a = new Field();

        if(args.length == 0){
            a.setMineField("*...\n..*.\n...."); // 0 Program arguments
        }else{
            a.setMineField(args[0]); // 1 Program arguments taken form user
        }

        a.showField();
        a.getHintField();
        a.showHint();
        a.showOutput();



    }




}
