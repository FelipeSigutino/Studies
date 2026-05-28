
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

public class LanguageCharacteristic {
    String Name;
    HashMap< Character,Double> frequency = new HashMap<>();
}
public LanguageCharacteristic CreateLanguage(String name, FileCharacteristic file) {
    LanguageCharacteristic language = new LanguageCharacteristic();
    language.Name = name;
    language.frequency = new HashMap<>(file.frequency); // COPY
    return language;
}

public class FileCharacteristic {
    HashMap< Character,Integer> lettercount = new HashMap<>();
    HashMap< Character,Double> frequency = new HashMap<>();
}
public LanguageCharacteristic AssignFileLanguageCharesteristicc(FileCharacteristic file) {
    LanguageCharacteristic File = new LanguageCharacteristic();
    File.frequency = new HashMap<>(file.frequency);
    return File;
}

String NormalizeText(String text) {
    text = text.toLowerCase();
    text = text.replaceAll("[^\\p{ASCII}]", "");
    return text;
}

public class ReadFile {
    FileCharacteristic file = new FileCharacteristic();
    int numberOfChars = 0;
    public FileCharacteristic main (String filename) {
        File myObj = new File(filename);
        try (Scanner MyScanner = new Scanner(myObj)) {
            while (MyScanner.hasNextLine()) {
                String data = MyScanner.nextLine();
                data = NormalizeText(data);
                for (char c : data.toCharArray()) {
                    if (Character.isLetter(c)) {
                        file.lettercount.put(c, file.lettercount.getOrDefault(c, 0) + 1);
                        numberOfChars++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

    public void calculatefrequency() {
        for (Character key : file.lettercount.keySet()) {
            int count = file.lettercount.get(key);
            double frequencyOfChar = (double) count/numberOfChars;
            file.frequency.put(key, frequencyOfChar);

        }
        numberOfChars = 0;
    }

    public LanguageCharacteristic SaveLanguague(String Name) {
        LanguageCharacteristic language = new LanguageCharacteristic();
        language.Name = Name;
        language.frequency = file.frequency;
        return language;
    }

    public void printLanguage (LanguageCharacteristic language) {
        for (Map.Entry< Character, Double> entry : language.frequency.entrySet()) {
            println(entry.getKey() + " : " + entry.getValue());
        }
        println(language.Name);
    }
    public FileCharacteristic ClearFileData() {
        file.lettercount.clear();
        file.frequency.clear();
        return file;
    }
}

public double distance(LanguageCharacteristic a, LanguageCharacteristic b) {
    double sum = 0.0;
    for (char c = 'a'; c <= 'z'; c++) {
        double fa = a.frequency.getOrDefault(c, 0.0);
        double fb = b.frequency.getOrDefault(c, 0.0);
        sum += Math.pow(fa - fb, 2);
    }
    return Math.sqrt(sum);
}

public String FindLanguage(
        LanguageCharacteristic unknown, List<LanguageCharacteristic> knownlanguages) {
    LanguageCharacteristic nearest = null;
    double minDistance = Double.MAX_VALUE;
    for (LanguageCharacteristic language : knownlanguages) {
        double distance = distance(unknown, language);
        if (distance < minDistance) {
            minDistance = distance;
            nearest = language;
        }
    }
    return nearest.Name;
}


void main() {
    clrscr();
    ReadFile readFile = new ReadFile();

    FileCharacteristic polishFile = readFile.main("Base Polish File.txt");
    readFile.calculatefrequency();
    LanguageCharacteristic polish = CreateLanguage("Polish", polishFile);
    readFile.printLanguage(polish);
    readFile.ClearFileData();

    FileCharacteristic englishFile = readFile.main("Base English File.txt");
    readFile.calculatefrequency();
    LanguageCharacteristic english = CreateLanguage("English", englishFile);
    readFile.printLanguage(english);
    readFile.ClearFileData();

    List<LanguageCharacteristic> known = List.of(polish, english);

    FileCharacteristic TestFile = readFile.main("Test File.txt");
    readFile.calculatefrequency();
    LanguageCharacteristic test = AssignFileLanguageCharesteristicc(TestFile);

    print("Most propably the File Language is " +FindLanguage(test, known));

}