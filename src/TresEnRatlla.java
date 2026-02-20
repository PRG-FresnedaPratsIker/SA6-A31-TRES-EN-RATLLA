import java.io.FileNotFoundException;

public class TresEnRatlla {

    private Tauler tauler;
    private Jugador[] jugadors;
    private Simbols simbols;


    public TresEnRatlla() throws FileNotFoundException {
        int dimensio = 3;

        boolean diferent = GestorIO.solicitarConfirmacio("Vols un tauler de dimensio diferent de " + dimensio + "x" + dimensio + "?");
        if (diferent) {
            while (true) {
                int valor = GestorIO.llegirEnter("Introdueix la nova dimensio (mínim 3): ");
                if(valor >= 3 && valor <= 8) {
                    dimensio = valor;
                    break;
                }
                System.out.println("Error: La dimensio ha d'estar entre 3 i 8.");
            }
        }

        simbols = new Simbols();
        simbols.seleccionar();

        this.tauler = new Tauler(dimensio, simbols);



        this.jugadors = new Jugador[2];
        this.jugadors[0] = new Jugador(EstatCasella.FITXA_X, simbols);
        this.jugadors[1] = new Jugador(EstatCasella.FITXA_O, simbols);
    }


    public void jugar() {
        tauler.buidar();
        tauler.mostrar();

        int torn = 0;

        while (true) {
            jugadors[torn].posarFitxa(tauler);
            tauler.mostrar();

            if(tauler.hiHaTresEnRatlla()) {
                jugadors[torn].cantarVictoria();
                break;
            }

            if (tauler.estaPle()) {
                System.out.println("Empat! El tauler està ple i no hi ha guanyador.");
                break;
            }

            torn = 1 - torn;
        }
        GestorIO.tancar();
    }

    public static void main(String[] args) throws FileNotFoundException {
        TresEnRatlla joc = new TresEnRatlla();
        joc.jugar();
    }
}
