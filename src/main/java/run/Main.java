package run;

import area.Field;

/**
 * <h1>run.MineSweeper Game<h1/>
 * The run.MineSweeper implements an application that
 * simply generate field of N x M squares represented game field
 * for run.MineSweeper game.
 * This application can also return hint-field.
 * <p>
 * Rules and input-output value are describe in the
 * run.MineSweeper interface.
 * <p/>
 *
 * @author Dawid Janik
*/
public class Main {


    /**
     * This is the main method which makes use of setMineFiled and showFiled method
     * taken from run.MineSweeper interface.
     * @param args unused or used depends of method giving a initial value.
     * @return Nothing
     */
    public static void main(String[] args) {

        System.out.println("Running App run.MineSweeper\n");

        Field a = new Field();

        if(args.length == 0){
            a.setMineField("*...\n..*.\n...."); // 0 Program arguments
        }else{
            a.setMineField(args[0]); // 1 Program arguments taken form user
        }

        a.showField();
        System.out.println(a.getHintField());
//      a.showHint();

    }


}
