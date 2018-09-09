package pl.dsyou.minesweeper.area.model.field;

/**
 * @author Dawid Janik
 * @since 09.09.2018
 */
public enum FieldType {
    MINE("*"),
    NORAML("");

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

    FieldType(String type) {
        this.type = type;
    }
}
