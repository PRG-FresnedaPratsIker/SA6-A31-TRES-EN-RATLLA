import java.io.InputStream;
import java.util.Scanner;

public class GestorIO {
    static Scanner sc = new Scanner(System.in);

    static void __setInputStreamForTests(InputStream in) {
        sc = new Scanner(in);
    }

    public static int llegirEnter(String missatge) {
        System.out.print(missatge);

        while (true) {
            String linia = sc.nextLine().trim();

            if (linia.matches("-?\\d+")) {
                try {
                    return Integer.parseInt(linia);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El número està fora del rang d'un int.");
                }
            } else {
                System.out.println("Error: Introdueix un número enter vàlid.");
            }

            System.out.print(missatge);
        }
    }

    public static String llegirText(String missatge) {
        System.out.print(missatge);
        return sc.nextLine();
    }

    public static boolean solicitarConfirmacio(String missatge) {
        while (true) {
            System.out.print(missatge + " (s/n): ");
            String resposta = sc.nextLine().trim().toLowerCase();

            if (resposta.matches("[sn]")) {
                return resposta.equals("s");
            }
        }
    }


    public static String llegirLletraGuioNumero(String missatge) {
        System.out.print(missatge);

        while (true) {
            String entrada = sc.nextLine().trim();

            if (entrada.matches("[A-Za-z]-\\d")) {
                return entrada.substring(0, 1).toUpperCase() + entrada.substring(1);
            }

            System.out.println("Error: Format incorrecte. Exemple vàlid: A-1");
            System.out.print(missatge);
        }
    }

    public static void tancar() {
        sc.close();
    }
}
