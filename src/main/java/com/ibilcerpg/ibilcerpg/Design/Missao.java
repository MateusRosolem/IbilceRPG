package com.ibilcerpg.ibilcerpg.Design;
import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;

/**
 * Classe da missao, que armazena a quantidade, quais inimigos devem ser derrotados, tambem a quem requisitou a missao e
 * a recompensa
 * @param <R> recompensa da missao quando concluida
 */
public class Missao<R extends Item> implements Serializable {


    /**
     * Metodo que verifica se a missao foi concluida, se nao mostra quanto falta para completar
     * @return retorna true se foi concluida, false se nao
     */
    public boolean done(FXCombateController UI){
        if((Integer)n<=0){
            return true;
        }else{
            UI.imprimirTexto("Ainda falta derrotar " + getN() + " " + getInimigo() + " para completar a missão.");
        } return false;
    }



    private boolean concluida;
    private String nome;
    private int n;
    private int total;
    private String inimigo;

    private String requisitou;
    private R r;

    public Missao(String inimigo, R r, String nome, int n, String requisitou) {
        this.inimigo = inimigo;
        this.r = r;
        this.nome = nome;
        this.n = n;
        this.requisitou = requisitou;
        this.concluida = false;
    }

    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public String getInimigo() {
        return inimigo;
    }
    public void setE(String e) {
        this.inimigo = e;
    }
    public R getR() {
        return r;
    }
    public void setR(R r) {
        this.r = r;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRequisitou() {
        return requisitou;
    }

    public void setRequisitou(String requisitou) {
        this.requisitou = requisitou;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
