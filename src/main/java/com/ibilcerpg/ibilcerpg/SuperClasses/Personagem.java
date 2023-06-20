package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;

/**
 * Classe pai de todas as entidades que atuam em combate, tem todos os atributos e metodos base que todas as classes filhas usam
 */
public class Personagem implements Serializable {
    private String nome;
    private boolean vivo;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataqueBase;
    private int defesaBase;
    private float multiplicadorAtaque;
    private float multiplicadorDefesa;
    private float debuffDano;
    private int contadorTurnos;

    public Personagem(String nome, boolean vivo, int vidaMaxima, int vidaAtual, int ataqueBase, int defesaBase, float multiplicadorAtaque, float multiplicadorDefesa, float debuffDano) {
        this.nome = nome;
        this.vivo = vivo;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaAtual;
        this.ataqueBase = ataqueBase;
        this.defesaBase = defesaBase;
        this.multiplicadorAtaque = multiplicadorAtaque;
        this.multiplicadorDefesa = multiplicadorDefesa;
        this.debuffDano = debuffDano;
    }

    /**
     * Método base para as classes filhas receberem dano, diminuindo a vida atual de acordo.
     *
     * @param danoPuro recebe o dano dado pelo inimigo para fazer alterações com base na defesa
     * @return retorna o dano recebido pela entidade para ser apresentado
     */
    public int receberDano(float danoPuro){
        
        float dano = danoPuro - ((Float)(getDefesaBase()*getMultiplicadorDefesa()));
        if(dano < 0 ) dano = 0f;
        setVidaAtual(getVidaAtual()-Math.round(dano));
        return Math.round(dano);
    }

    /**
     * Método base para as classes filhas receberem cura, aumentando a vida atual de acordo, mas nao mais que a vida maxima
     *
     * @param cura aumenta a vida atual de acordo com esse valor.
     * @return retorna o valor curado para ser apresentado.
     */
    public int receberCura(float cura){
        if(getVidaAtual() + Math.round(cura) >= getVidaMaxima()){
            setVidaAtual(getVidaMaxima());
            System.out.println( getNome() + " foi curado em " + Math.round(cura) + " pontos de vida");
            return Math.round(cura);
        }else{
            setVidaAtual(getVidaAtual() + Math.round(cura));
            System.out.println( getNome() + " foi curado em " + Math.round(cura) + " pontos de vida");
            return Math.round(cura);
        }
        
    }

    /**
     * metodo que checa se a entidade esta com vida menor ou igual a zero, se sim, atualiza a variavel Vivo e retorna ela
     *
     * @return retorna a variavel vivo depois de atualiza-la ou nao
     */
    public boolean estaVivo(){
        if(getVidaAtual()<=0) setVivo(false);
        return vivo;
    }

    /**
     * Metodo base para as classes filhas darem Override com suas especificacoes
     *
     * @return returna null por ser o metodo base
     */
    public Acao<String,Object> turnoNoCombate(){
        return null;
    }


    /**
     * incrementa o contador de turnos em 1 toda vez que é chamado, ajuda as classes filhas a saberem qual turno estao por varios usos
     *
     */
    public void incrementarContadorTurnos(){
        setContadorTurnos(getContadorTurnos()+1);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    public boolean getVivo() { return vivo;}
    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }
    public String getNome() {
        return nome;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public float getMultiplicadorAtaque() {
        return multiplicadorAtaque;
    }
    public void setMultiplicadorAtaque(float multiplicadorAtaque) {
        this.multiplicadorAtaque = multiplicadorAtaque;
    }
    public float getMultiplicadorDefesa() {
        return multiplicadorDefesa;
    }
    public void setMultiplicadorDefesa(float multiplicadorDefesa) {
        this.multiplicadorDefesa = multiplicadorDefesa;
    }
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }
    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }
    public void setDefesaBase(int defesaBase) {
        this.defesaBase = defesaBase;
    }
    public int getVidaAtual() {
        return vidaAtual;
    }
    public int getAtaqueBase() {
        return ataqueBase;
    }
    public int getDefesaBase() {
        return defesaBase;
    }

    public float getDebuffDano() {
        return debuffDano;
    }
    public void setDebuffDano(float debuffDano) {
        this.debuffDano = debuffDano;
    }
    public int getContadorTurnos() {
        return contadorTurnos;
    }
    public void setContadorTurnos(int contadorTurnos) {
        this.contadorTurnos = contadorTurnos;
    }

}
