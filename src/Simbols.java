import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Simbols {
    private String[][] parelles;
    private int parellesSeleccionada;
    private int numParelles;

    public Simbols() throws FileNotFoundException {
        this.parellesSeleccionada = 0;
        carregar();
    }

    public void carregar() throws FileNotFoundException {
        File fitxer = new File("resources/config.joc");

        if(!fitxer.exists()) {
            errorExit("No s'ha trobat el fitxer config.joc", 1);
        }


        Scanner fsc = new Scanner(fitxer);


        if(!fsc.hasNextLine()) {
            errorExit("El fitxer esta buit", 1);
        }

        String primera = fsc.nextLine().trim();

        if (!primera.matches("\\d+")) {
            errorExit("La primera linia ha de ser un numero enter", 1);
        }

        numParelles = Integer.parseInt(primera);

        if(numParelles < 1) {
            errorExit("El nombre de parelles ha de ser major que 0", 1);
        }

        parelles = new String[numParelles][2];

        char[] simbolsUsats = new char[numParelles * 2];
        int comptadorUsats = 0;

        int idx = 0;
        int linia = 2;

        while (fsc.hasNextLine()) {
            String l = fsc.nextLine();

            if(l.trim().isEmpty()) {
                linia++;
                continue;
            }

            if(!l.matches("^.{1} {1}.{1}$")) {
                errorExit("Format incorrecte a la linia " + linia + ". Format esperat: 'X Y'", linia);
            }

            char s1 = l.charAt(0);
            char s2 = l.charAt(2);

            if(s1 == s2) {
                errorExit("Els simbols d'una parella no poden ser iguals (linia " + linia + ")", linia);
            }

            if(jaUtilitzat(simbolsUsats, comptadorUsats, s1) || jaUtilitzat(simbolsUsats, comptadorUsats, s2)) {
                errorExit("Els simbols d'una parella no poden ser iguals a altres parelles (linia " + linia + ")", linia);
            }

            if(idx >= numParelles) {
                errorExit("S'han trobat més parelles de les indicades a la primera linia (linia " + linia + ")", linia);
            }

            parelles[idx][0] = String.valueOf(s1);
            parelles[idx][1] = String.valueOf(s2);

            simbolsUsats[comptadorUsats++] = s1;
            simbolsUsats[comptadorUsats++] = s2;

            idx++;
            linia++;
        }

        if(idx != numParelles) {
            errorExit("S'han trobat menys parelles de les indicades a la primera linia", linia);
        }

    }

    public void seleccionar() {
        while (true) {
            System.out.println("Selecciona una parella de simbols:");
            for(int i = 0; i < numParelles; i++) {
                System.out.println((i + 1) + ") " + parelles[i][0] + " " + parelles[i][1]);
            }
            System.out.println((numParelles + 1) + ") Aleatori");

            int op = GestorIO.llegirEnter("Opcio: ");

            if (op >= 1 && op <= numParelles) {
                parellesSeleccionada = op - 1;
                return;
            }
            if (op == numParelles + 1) {
                parellesSeleccionada = new Random().nextInt(numParelles);
                return;
            }
            System.out.println("Error: Opcio no valida");

        }

    }


    public String obtindreSimbol(EstatCasella tipus) {
        if(tipus == EstatCasella.FITXA_X) {
            return parelles[parellesSeleccionada][0];
        }
        if(tipus == EstatCasella.FITXA_O) {
            return parelles[parellesSeleccionada][1];
        }
        return " ";
    }


    private boolean jaUtilitzat(char[] usats, int total, char simbol) {
        for(int i = 0; i < total; i++) {
            if(usats[i] == simbol) {
                return true;
            }
        }
        return false;
    }

    private void errorExit(String msg, int linia) {
        System.out.println("Error (linia " + linia + "): " + msg);
        System.exit(1);
    }


}
