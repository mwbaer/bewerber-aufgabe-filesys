# Filesystem

erstellt von Magnus Baer, 03.03.2023

## Befehle:
ls ([folder])-> alle Inhalte im aktuellen Folder mit Größe anschauen oder von [folder], wenn angegeben
mkdir [name] -> neuen Folder mit [name] erstellen
touch [name] [size] -> neue Datei mit [name] und [size] erstellen
cd ([folder]) -> zum root Folder gehen oder zu [folder], wenn angegeben
rm [item] -> angegebenes item löschen
exit -> Programm beenden
restart -> alle verzeichnisse und dateien (außer root) löschen

## Tests 
> Kopiere die folgenden Zeilen in die Command-Line um das Programm zu testen.


### Test wie im Beispiel [Result: 17]
restart; ls; mkdir Folder1; cd Folder1; touch File11 3; touch File12 7; cd; mkdir Folder2; cd Folder2; touch File21 5; cd; touch File0 2; ls

### Test 2 [Result: 18] 
restart; ls; mkdir Folder1; mkdir Folder2; cd Folder2; touch File1 3; cd; touch File2 8; mkdir Folder3; cd Folder3; touch File32 2; cd; touch File34 5; ls

### Test 3 [Result: 3]
restart; ls; mkdir Folder3; cd Folder3; touch File12 1; cd; touch File3 2; mkdir Folder4; mkdir Folder2; ls




