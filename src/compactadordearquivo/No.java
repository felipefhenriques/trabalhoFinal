/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactadordearquivo;

/**
 *
 * @author Felipe
 */
public class No {
    
    protected String elemento;
    protected No proximo;

    public No(String elemento, No proximo) {
        this.elemento = elemento;
        this.proximo = proximo;
    }

    String getElemento() {
        return elemento;
    }

    No getProximo() {
        return proximo;
    }

    void setProximo(No proximo) {
        this.proximo = proximo;
    }
    
}
