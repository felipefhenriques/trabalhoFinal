/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactadordearquivo;

/**
 *
 * @author Luciana Alves
 */
class No {

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

public class Lista {

    private No noInicial;
    private int tamanho = 0;

    public int getTamanho() {
        return tamanho;
    }

    public void adicionarElemento(String elemento) {
        if (noInicial == null) {
            this.noInicial = new No(elemento, null);
        } else {
            No auxiliar = noInicial;
            while (auxiliar.proximo != null) {
                auxiliar = auxiliar.proximo;
            }
            auxiliar.proximo = new No(elemento, null);
        }
        tamanho++;
    }

    public void insereInicio(String elemento) {
        No novo = new No(elemento, noInicial);
        noInicial = novo;
    }

    public int buscarElemento(String elemento) {
        No auxiliar = this.noInicial;
        int cont = 0;
        while (auxiliar != null) {
            if (elemento.equals(auxiliar.elemento)) {
                return cont;
            }
            cont++;
            auxiliar = auxiliar.proximo;
        }

        return -1;
    }

    public String buscarElementoPorPosicao(int posicao) {
        No auxiliar = this.noInicial;
        int cont = 0;
        while (auxiliar != null && cont < posicao) {
            cont++;
            auxiliar = auxiliar.proximo;
        }
        if(auxiliar != null && cont == posicao){
            return auxiliar.elemento;
        }else{
            return null;
        }
        
    }

    public boolean removerElemento(String elemento) {
        if (this.noInicial == null) {
            return false;
        }
        No auxiliar = noInicial;
        No anterior = null;

        while (auxiliar != null && !auxiliar.getElemento().equals(elemento)) {
            anterior = auxiliar;
            auxiliar = auxiliar.getProximo();
        }
        //Se a lista tem apenas um nó
        if (anterior == null) {
            noInicial = noInicial.getProximo(); //1. remove do início
        } else {
            if (auxiliar != null && auxiliar.getElemento().equals(elemento)) {
                anterior.setProximo(auxiliar.getProximo());
            } else {
                return false;
            }
        }
        return true;
    }

    public void imprimir() {
        No auxiliar = noInicial;
        while (auxiliar != null) {
            System.out.print(auxiliar.elemento + ", ");
            auxiliar = auxiliar.proximo;
        }
        System.out.println("");
    }

}
