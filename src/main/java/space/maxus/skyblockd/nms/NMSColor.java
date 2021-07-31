package space.maxus.skyblockd.nms;

import org.jetbrains.annotations.NotNull;

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

    private final @NotNull String jsonColor;

    public @NotNull String getJson() { return jsonColor; }

    NMSColor() {
        jsonColor = this.name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public @NotNull String toString() {
        return "\"color\": \""+jsonColor+"\"";
    }
}
