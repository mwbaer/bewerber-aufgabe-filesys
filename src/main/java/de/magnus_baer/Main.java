package de.magnus_baer;

import de.magnus_baer.dir.DirectoryItem;
import de.magnus_baer.dir.File;
import de.magnus_baer.dir.Folder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Folder currentFolder;
    private static Folder root = new Folder("root");
    private static boolean cont = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        currentFolder = root;
        completeInput("help");
        while (cont) {
            print(currentFolder.name() + " $ ");
            String input = scanner.nextLine();
            for (String cmd : input.split("; ")) if (!cmd.isEmpty()) {
                completeInput(cmd);
            }
            print("\n");
        }
    }

    private static void completeInput (String input) {
        String[] arguments = toArgs(input);
        if (arguments.length > 0) {
            String firstArg = getOrNull(arguments, 1);
            String secondArg = getOrNull(arguments, 2);
            switch (arguments[0]) {
                case "ls" -> completeLs(firstArg);
                case "mkdir" -> completeMkdir(firstArg);
                case "touch" -> completeTouch(firstArg, secondArg);
                case "cd" -> completeCd(firstArg);
                case "rm" -> completeRemove(firstArg);
                case "restart" -> completeRestart();
                case "exit" -> completeBreak();
                default -> completeHelp();
            }
        }
    }

    private static void completeRestart () {
        root = new Folder("root");
        currentFolder = root;
    }

    private static void completeLs (String folderName) {
        if (folderName == null) {
            printFolderLs(currentFolder, 0);
        } else {
            DirectoryItem item = currentFolder.getItem(folderName);
            if (item == null) println("This item does not exist");
            else if (!(item instanceof Folder folder)) println("This item is not a Folder!");
            else printFolderLs(folder, 0);
        }
    }

    private static void completeMkdir (String folderName) {
        if (folderName == null) println("Name can't be null!");
        else {
            if (currentFolder.newItem(new Folder(folderName)))
                println("New Folder " + folderName + " created");
            else println("Folder couldn't be created!");
        }
    }

    private static void completeTouch (String fileName, String fileSize) {
        int size = 0;
        if (fileName == null) println("File name can't be null");
        else if (fileSize == null) println("Please provide a file size!");
        else {
            try {
                size = Integer.parseInt(fileSize);
            } catch (NumberFormatException ignored) {/*ignored*/}
            if (currentFolder.newItem(new File(fileName, size))) {
                println("New File " + fileName + " created with size " + size);
            } else println("File couldn't be created!");
        }
    }

    private static void completeCd (String folderName) {
        if (folderName == null) {
            Main.currentFolder = root;
        } else {
            DirectoryItem item = currentFolder.getItem(folderName);
            if (item == null) println("This folder does not exist!");
            else if (!(item instanceof Folder folder)) println("This item is not a folder!");
            else {
                Main.currentFolder = folder;
            }
        }
    }

    private static void completeHelp () {
        print(
                """
                ls ([folder]): lists all contents and the size of the current folder or of folder, if specified.
                cd [folder]: goes to the specified folder
                mkdir [name]: creates a new folder inside the current one with the given name
                touch [name] [size]: creates a new file inside the current folder with the given name and size
                rm [item]: delete item
                restart: clear all files and folder
                exit: ends the program and deletes all the data
                everything else: shows the help
                """
        );
    }

    private static void completeRemove (String itemName) {
        if (itemName == null) println("No name provided");
        else {
            if (currentFolder.removeItem(itemName)) println("Item removed!");
            else println("Item not found.");
        }
    }

    private static void completeBreak () {
        println("Goodbye!");
        cont = false;
    }

    /**
     *
     * @param input The command line input
     * @return The input split in words
     */
    private static String @NotNull [] toArgs (@NotNull String input) {
        String[] split = input.split(" ");
        List<String> fSplit = new ArrayList<>();
        for (String subStr : split) if (!subStr.isEmpty()) fSplit.add(subStr);
        return fSplit.toArray(new String[]{});
    }

    private static void printFolderLs (@NotNull Folder folder, int indent) {
        String indentString = "";
        for (int i = 0; i < indent; i++) indentString = indentString.concat(" ");
        indentString = indentString.concat("|-> ");
        println(indentString + "[" + folder.name() + ", Folder, " +  folder.size() + " Bytes]");
        for (DirectoryItem item : folder.getContent()) {
            if (item instanceof File) {
                println("    " + indentString + item.name() + ", " + item.typeName() + ", " + item.size() + " Bytes");
            } else if (item instanceof Folder subFolder) {
                printFolderLs(subFolder, indent + 4);
            }
        }
    }

    private static void println(String print) {
        System.out.println(print);
    }

    private static void print(String print) {
        System.out.print(print);
    }

    /**
     *
     * @return The array's index-th element when the array contains it, else null.
     */
    @Contract(pure = true)
    private static @Nullable String getOrNull (String @NotNull [] array, int index) {
        if (array.length >= index + 1) return array[index];
        else return null;
    }


}
