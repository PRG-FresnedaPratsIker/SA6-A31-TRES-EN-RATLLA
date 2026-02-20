public class Coordenada {

    private char fila;
    private int columna;

    public Coordenada (char fila, int columna) {
        this.fila = Character.toUpperCase(fila);
        this.columna = columna;
    }

    public char getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isValida(int dimensio) {
        char lletraMax = (char) ('A' + dimensio - 1);

        if (fila < 'A' || fila > lletraMax) {
            return false;
        }

        if(columna < 1 || columna > dimensio) {
            return false;
        }
        return true;
    }

}
