import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("1.Use reletional between node");
            System.out.println("2.Use graph as a middle to store data");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    new useReletion().run();
                    break;
                case 2:
                    new useGraph().run();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
