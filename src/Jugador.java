public class Jugador {

    private final EstatCasella tipus;

    public Jugador(EstatCasella tipus) {
        this.tipus = tipus;
    }

    public void posarFitxa(Tauler tauler) {
        while (true) {
            Coordenada c = obtenirCoordenada(tauler);

            if(tauler.isOcupada(c)) {
                System.out.println("Error: Aquesta casella ja està ocupada");
                continue;
            }

            tauler.posarFitxa(c, tipus);
            break;
        }
    }


    private Coordenada obtenirCoordenada(Tauler tauler) {
        while (true) {
            String entrada = GestorIO.llegirLletraGuioNumero("Jugador " + simbolFitxa() + ", introdueix la coordenada (ex. A-1): ");

            char fila = Character.toUpperCase(entrada.charAt(0));
            int columna = Integer.parseInt(entrada.substring(2));

            Coordenada c = new Coordenada(fila, columna);


            if(!tauler.isCoordenadaValida(c)) {
                System.out.println("Error: Coordenada fora del tauler.");
                continue;
            }

            return c;

        }
    }


    public void cantarVictoria() {
        System.out.println("Jugador " + simbolFitxa() + " ha guanyat la partida!");
    }


    private char simbolFitxa() {
        return tipus == EstatCasella.FITXA_X ? 'X' : 'O';
    }
}
