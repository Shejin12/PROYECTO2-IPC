package com.mycompany.proyectojuegos.TorreHanoi.Pila;

public class Pila {
    private Nodos Cabeza;
    private int ContadorNodos;

    public Nodos getCabeza() {
        return Cabeza;
    }

    public int getContadorNodos() {
        return ContadorNodos;
    }
    
    public void Colocar(Nodos Nuevo ){
        ContadorNodos++;
        if (Cabeza==null) {
            Cabeza = Nuevo;
        } else {
            Nuevo.setAbajo(Cabeza);
            Cabeza.setArriba(Nuevo);
            Cabeza = Nuevo;
        }
    }
    
    public void Quitar (){
        if (ContadorNodos > 0) {
            ContadorNodos--;
            Cabeza = Cabeza.getAbajo();
        }
    }
    
    public String regreso(){
        return Cabeza.getDato();
    }
}
