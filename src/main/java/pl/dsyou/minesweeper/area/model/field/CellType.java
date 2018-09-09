package pl.dsyou.minesweeper.area.model.field;

/**
 * @author Dawid Janik
 * @since 08.04.2018
 */
public enum CellType {
    mine( "*"),
    normal("");

    private String type;

    @Override
    public String toString() {
        return "CellType{" +
                "type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    CellType(String type) {
        this.type = type;
    }
}
