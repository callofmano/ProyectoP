package com.mycompany.poo2.modelo;


public class Premio {
    private Posicion pos;
    
    private String descripcion;
    
    private Auspiciante auspiciante;

    public Premio(Posicion pos, String descripcion, Auspiciante auspiciante) {
        this.pos = pos;
        this.descripcion = descripcion;
        this.auspiciante = auspiciante;
    }

    @Override
    public String toString() {
        return "Premio{" + "pos=" + pos + ", descripcion=" + descripcion + ", auspiciante=" + auspiciante + '}';
    }
    
    
    
}
