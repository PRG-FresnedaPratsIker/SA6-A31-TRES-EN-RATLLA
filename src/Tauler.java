import static java.lang.Character.toUpperCase;

public class Tauler {

    private final int dimensio;
    private final EstatCasella[][] caselles;


    public Tauler(int dimensio) {
        this.dimensio = dimensio;
        this.caselles = new EstatCasella[dimensio][dimensio];
        buidar();
    }


    public void buidar() {
        for(int i = 0; i < dimensio; i++) {
            for (int j = 0; j < dimensio; j++) {
                caselles[i][j] = EstatCasella.BUIT;
            }
        }
    }

    public boolean isCoordenadaValida(Coordenada coord) {
        return coord != null && coord.isValida(dimensio);
    }

    public void mostrar() {
        System.out.print("  ");
        for (int i = 1; i <= dimensio; i++){
            System.out.print(" " + i + " ");
        }
        System.out.println();

        System.out.print("  ");
        for (int i = 0; i < dimensio; i++) {
            System.out.print("--- ");
        }
        System.out.println();


        for (int i = 0; i < dimensio; i++) {
            char lletraFila = (char) ('A' + i);
            System.out.print(" " + lletraFila + " ");
            for (int j = 0; j < dimensio; j++) {
                System.out.print("|");
                System.out.print(" " + simbol(caselles[i][j]) + " ");
            }
            System.out.println("|");

            System.out.print("  ");
            for (int j = 0; j < dimensio; j++) {
                System.out.print("--- ");
            }
            System.out.println();
        }
    }

    private char simbol(EstatCasella estat) {
        if (estat == null) return ' ';
        return switch (estat) {
            case BUIT -> ' ';
            case FITXA_X -> 'X';
            case FITXA_O -> 'O';
        };
    }

    public void posarFitxa(Coordenada coord, EstatCasella tipus) {
        int f = filaIndex(coord);
        int c = colIndex(coord);
        caselles[f][c] = tipus;
    }

    public boolean isOcupada(Coordenada coord) {
        int f = filaIndex(coord);
        int c = colIndex(coord);
        return caselles[f][c] != EstatCasella.BUIT;
    }

    public boolean estaPle() {
        for (int i = 0; i < dimensio; i++) {
            for (int j = 0; j < dimensio; j++) {
                if (caselles[i][j] == EstatCasella.BUIT) return false;
            }
        }
        return true;
    }

    public boolean hiHaTresEnRatlla() {
        return hiHaTresEnRatlla(EstatCasella.FITXA_X) || hiHaTresEnRatlla(EstatCasella.FITXA_O);
    }


    private boolean hiHaTresEnRatlla(EstatCasella tipus) {

        for (int i = 0; i < dimensio; i++) {
            boolean tot = true;
            for (int j = 0; j < dimensio; j++) {
                if (caselles[i][j] != tipus) {
                    tot = false;
                    break;
                }
            }
            if (tot) return true;
        }

        for (int i = 0; i < dimensio; i++) {
            boolean tot = true;
            for (int j = 0; j < dimensio; j++) {
                if (caselles[j][i] != tipus) {
                    tot = false;
                    break;
                }
            }
            if (tot) return true;
        }

        boolean totDiag1 = true;
        for (int i = 0; i < dimensio; i++) {
            if (caselles[i][i] != tipus) {
                totDiag1 = false;
                break;
            }
        }
        if (totDiag1) return true;

        boolean totDiag2 = true;
        for (int i = 0; i < dimensio; i++) {
            if (caselles[i][dimensio - 1 - i] != tipus) {
                totDiag2 = false;
                break;
            }
        }
        return totDiag2;
    }



    private int filaIndex(Coordenada coord) {
        return Character.toUpperCase(coord.getFila()) - 'A';
    }

    private int colIndex(Coordenada coord) {
        return coord.getColumna() - 1;
    }

    public int getDimensio() {
        return dimensio;
    }

}
