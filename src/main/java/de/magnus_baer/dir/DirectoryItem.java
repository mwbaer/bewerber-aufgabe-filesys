package de.magnus_baer.dir;

import org.jetbrains.annotations.NotNull;

public interface DirectoryItem {

    @NotNull
    String name();

    int size();

    String typeName();


}
