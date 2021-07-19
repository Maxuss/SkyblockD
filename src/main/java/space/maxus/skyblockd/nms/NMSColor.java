package space.maxus.skyblockd.nms;

import java.util.Locale;

public enum NMSColor {
    BLACK(),
    DARK_BLUE(),
    DARK_GREEN(),
    DARK_AQUA(),
    DARK_RED(),
    DARK_PURPLE(),
    GOLD(),
    GRAY(),
    DARK_GRAY(),
    BLUE(),
    GREEN(),
    AQUA(),
    RED(),
    LIGHT_PURPLE(),
    YELLOW(),
    WHITE()

    ;

    private final String jsonColor;

    public String getJson() { return jsonColor; }

    NMSColor() {
        jsonColor = this.name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String toString() {
        return "\"color\": \""+jsonColor+"\"";
    }
}
