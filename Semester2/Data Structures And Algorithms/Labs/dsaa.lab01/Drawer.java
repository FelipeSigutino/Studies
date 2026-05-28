package dsaa.lab01;

public class Drawer {
	private static void drawLine(int n, char ch) {
        for(int i = 0; i < n; i++){
            System.out.print(ch);
        }
	}

    private static void lineOfCharSurroundedByCharLine(int firstCharLenght, int secondCharLenght, char ch1, char ch2) {
        drawLine(secondCharLenght, ch2);
        drawLine(firstCharLenght, ch1);
        drawLine(secondCharLenght, ch2);
        System.out.println();
    }


	public static void drawPyramid(int n) {
        int dotLineLenght = n - 1;
        int XLineLenght = 1;
		for(int i = 0; i < n; i++){
            lineOfCharSurroundedByCharLine(XLineLenght + 2*i,dotLineLenght - i , 'X', '.');
        }

	}
	
	public static void drawChristmassTree(int n) {
        int dotLineLenght = n - 1;
        int XLineLenght = 1;
        for(int j = 1; j <= n; j++) {
            for (int i = 0; i < j; i++) {
                lineOfCharSurroundedByCharLine(XLineLenght + 2*i,dotLineLenght - i , 'X', '.');
            }
        }
	}

    public static void drawLetterU(int n) {
        int dotLineLenght = n - 2;

        for (int i = 0; i < n - 1; i++) {
            lineOfCharSurroundedByCharLine(dotLineLenght,1,'.','X');
        }

        drawLine(n, 'X');
        System.out.println();

    }

}
//int dotLineLenght = n - 1;
//        int XLineLenght = 1;
//        for(int j = 0; j < n; j++) {
//
//            for (int i = 0; i < n; i++) {
//                drawLine(dotLineLenght - i, '.');
//                drawLine(XLineLenght + 2 * i, 'X');
//                drawLine(dotLineLenght - i, '.');
//                System.out.println();
//            }
//        }