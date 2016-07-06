/**
 * @author  Dawid Janik
 *
 * todo private
 */
public class Cell {

    public boolean isMine = false;
    public String  mine = "*";
    public String  noMine = "";

    public String value = "";
    public Integer hintValue = 0;

//===================================================================
//Constructors()

    public Cell() {
        super();
    }

    public Cell(Cell cell) {

        this.isMine = cell.isMine;
        this.value = cell.value;
        this.hintValue = cell.hintValue;

    }

    public Cell(Boolean isMine, String mine, String noMine) {
        this.isMine = isMine;
        this.mine = mine;
        this.noMine = noMine;
    }


//===================================================================
//Methods
    @Override
    public String toString() {
        return "Cell{" +
                "isMine=" + isMine +
               // ", mine='" + mine + '\'' +
                //", noMine='" + noMine + '\'' +
                '}';
    }




} // End of Cell
