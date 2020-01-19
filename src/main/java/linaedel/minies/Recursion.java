package linaedel.minies;

public class Recursion {
    public static void main(String[] args) throws InterruptedException {
        Long i = 10L;
        System.out.println("Factorial of " + i + " = " +factorial(i));
        System.out.println("First " + i +" of Fibonnacci numbers = " + fibonnacci(i));
    }

    public static Long factorial(Long number) {
        Long result = number;

        if (number == 0 || number == 1) {
            return 1L;
        }
            result = number * factorial(number-1);

        return result;
    }


    public static Integer fibonnacci(Long number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        if (number == 2) return 1;
        return fibonnacci(number - 1) + fibonnacci(number - 2);
    }
}
