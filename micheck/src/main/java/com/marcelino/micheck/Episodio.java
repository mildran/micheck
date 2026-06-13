package com.marcelino.micheck;

public class Episodio {
    private int numero;
    private boolean visto;

    public Episodio(int numero, boolean visto) {
        this.numero = numero;
        this.visto = visto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
}
