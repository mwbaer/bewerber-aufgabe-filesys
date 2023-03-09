package de.magnus_baer.dir;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record File(String name, int size) implements DirectoryItem {

    @Contract(pure = true)
    @Override
    public @NotNull String typeName () {
        return "File";
    }

}
