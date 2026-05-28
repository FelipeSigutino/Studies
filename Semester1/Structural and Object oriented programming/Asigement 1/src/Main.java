

//Ex1
public int sumUpTo(int n){
    int sum=0;
    for (int i = 1; i <= n; i++){
        sum+=i;
    }
    return sum;
}

//Ex2
public int factorial(int n){
    int factorial=1;
    for (int i = 1; i <= n; i++){
        factorial=factorial*i;
    }
    return factorial;
}

//Ex3
public String isEven(int n){
    String result;
    if (n%2==0){
        result = "true";
    }
    else{
        result = "false";
    }
    return result;
}

//Ex4
public int countDigits(int n){
    return String.valueOf(Math.abs(n)).length();
}
//(Note to Self) Math.abs(n) is a function that outputs absolute value of n

//Ex5
public int reverseNumber(int n){
    int rev=0;
    while (n!=0){
        rev=rev*10+n%10;
        n/=10;
    }
    return rev;
}
public int enter(){
    System.out.println(" ");
    return 1;
}
//Ex6
public String isPrime(int n){
    String result = "";
    if (n<=1){
        result = "false";
    }
    else if (n>=2) {
        for (int i = 2; i*i <= n; i++){
            if (n%i==0){
                result = "false";
            }
            else {
                result = "true";
            }
        }
    }
    return result;
}

//Ex7
public int sumEvenNumbers(int n){
    int sum=0;
    for (int i = 1; i <= n; i++){
        if (i%2==0){
            sum=sum+i;
        }
    }
    return sum;
}

//Ex8
public int power(int b,int e){
    int power=1;
    for (int i = 1; i <= e; i++){
        power=power*b;
    }
    return power;
}

//Ex9
public int printMultiplicationTable(int n){
    for (int i = 1; i <= n; i++){
        for (int j = 1; j <= n; j++){
            int a=i*j;
            if(countDigits(a)==1){
                System.out.print(a + "  ");
            } else if (countDigits(a)==2) {
                System.out.print(a + " ");
            } else if (countDigits(a)==3) {
                System.out.print(a);
            }

        }
        enter();
    }
    return 0;
}

//Ex10
public String isPalindrome(int n){
    String result = "";
    int og=n;
    int brand_new = reverseNumber(n);
    if (og == brand_new){
        result = "true";
    }
    else{
        result = "false";
    }
    return result;
}

//Ex11
public static int countVowels(String text){
        int vowels=0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if(c == 'a' || c=='e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            }

        }
        return vowels;
}

//Ex12
public int printFibonacci(int n){
    int a=0;
    int b=1;
    int c=0;
    for (int i = 1; i <= n; i++){
        System.out.print(c + " ");
        a=b;
        b=c;
        c=a+b;
    }
    return 0;
}

//Ex13
public int findMax(int a,int b,int c){
    int max=a;
    if (b>max){
        max=b;
    }
    if (c>max){
        max=c;
    }
    return max;
}

//Ex14
public int sumOfDigits(int n){
    int sum=0;
    while (n > 0){
        sum=sum+n%10;
        n/=10;
    }
    return sum;
}

//Ex15
public String isPerfect(int n){
    String result = "";
    int sum=0;
    int con=0;
    if (n==1){
        result = "false";
    }
    else if (n<=0){
        result = "false";
    }
    else{
        for (int i = 1; i <  n; i++){
            con=n%i;
            if (con==0){
                sum=sum+i;
            }
        }
        if (sum==n){
            result = "true";
        }
        else {
            result = "false";
        }
    }
    return result;
}


public void main() {
    System.out.print(" ");
}




