import java.util.Random;
import java.util.*;

int k = 10;
int n = 1000000;

String generateRandomString(int k) {
    String s = "";
    int leftLimit = 97;
    int rightLimit = 122;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(k);
    for(int i = 0; i < k; i++) {
        int randomLimitedInt = leftLimit + random.nextInt(rightLimit - leftLimit + 1);
        buffer.append((char)randomLimitedInt);

    }
    String generatedString = buffer.toString();

    System.out.print(generatedString + "\n");
    return generatedString;
}



void main() throws IOException {
    FileWriter myWriter = new FileWriter("test.txt");

    String[] array = new String[n];
    for (int i = 0; i < n; i++) {
        System.out.print("ld ");
        myWriter.write("ld ");


        array[i] = generateRandomString(k);
        myWriter.write(array[i] + "\n");
        System.out.println("eod");
        myWriter.write("eod\n");
    }
    for (int i = 0; i < n; i++) {
        myWriter.write("getdoc " + array[i] + "\n");
        myWriter.write("show" + "\n");
    }
    int kk = k + 1;

    for (int i = 0; i < n; i++) {
        myWriter.write("getdoc " + generateRandomString(kk)+ "\n");
        myWriter.write("show" + "\n");
    }
    myWriter.write("ht\n");
    myWriter.write("maxlen\n");
    myWriter.write("ha\n");
    myWriter.close();
}
