package area;

/**
 * This class is simply object structure of smallest part of field
 * called cell.
 * This cell's are representing state of mine-filed.
 *
 * @author  Dawid Janik
  */
public class Cell {

    public boolean isMine = false; //Means: False- no mine, True - mine cell
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
        return "area.Cell{" +
                "isMine=" + isMine +
               // ", mine='" + mine + '\'' +
                //", noMine='" + noMine + '\'' +
                '}';
    }




} // End of area.Cell
