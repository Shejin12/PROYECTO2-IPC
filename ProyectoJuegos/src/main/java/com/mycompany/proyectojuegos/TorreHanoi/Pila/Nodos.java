package com.mycompany.proyectojuegos.TorreHanoi.Pila;

public class Nodos {
    private String Dato;
    private Nodos Arriba;
    private Nodos Abajo;

    public String getDato() {
        return Dato;
    }

    public void setDato(String Dato) {
        this.Dato = Dato;
    }

    public Nodos getArriba() {
        return Arriba;
    }

    public void setArriba(Nodos Arriba) {
        this.Arriba = Arriba;
    }

    public Nodos getAbajo() {
        return Abajo;
    }

    public void setAbajo(Nodos Abajo) {
        this.Abajo = Abajo;
    }
}
