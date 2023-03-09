package de.magnus_baer.dir;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Folder implements DirectoryItem {

    private final String name;
    private DirectoryItem[] content;

    public Folder(String name) {
        this.name = name;
        this.content = new DirectoryItem[]{};
    }

    @NotNull
    @Override
    public String name() {
        return name;
    }


    @Override
    public int size() {
        int size = 0;
        for (DirectoryItem item : content) {
            size += item.size();
        }
        return size;
    }

    public DirectoryItem[] getContent () {
        return content;
    }

    @Nullable public DirectoryItem getItem (@NotNull String name) {
        DirectoryItem item = null;
        for (DirectoryItem it : content) if (it.name().equals(name)) {
            item = it;
            break;
        }
        return item;
    }

    public boolean newItem (@NotNull DirectoryItem item) {
        if (getItem(item.name()) == null) {
            List<DirectoryItem> lCntnt = new ArrayList<>(List.of(content));
            lCntnt.add(item);
            content = lCntnt.toArray(new DirectoryItem[]{});
            return true;
        } else return false;
    }

    public boolean removeItem (@NotNull String itemName) {
        boolean hasRemoved = false;
        List<DirectoryItem> lCntnt = new ArrayList<>(List.of(content));
        for (DirectoryItem item : lCntnt) {
            if (item.name().equals(itemName)) {
                hasRemoved = lCntnt.remove(item);
                break;
            }
        }
        content = lCntnt.toArray(new DirectoryItem[]{});
        return hasRemoved;
    }

    @Override
    public String typeName () {
        return "Folder";
    }

}
