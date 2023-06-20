package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;

public class Personagem implements Serializable {
    private String nome;
    private boolean vivo;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataqueBase;
    private int defesaBase;
    private int velocidade;
    private float multiplicadorAtaque;
    private float multiplicadorDefesa;
    private float debuffDano;

    public Personagem(String nome, boolean vivo, int vidaMaxima, int vidaAtual, int ataqueBase, int defesaBase, int velocidade, float multiplicadorAtaque, float multiplicadorDefesa, float debuffDano) {
        this.nome = nome;
        this.vivo = vivo;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaAtual;
        this.ataqueBase = ataqueBase;
        this.defesaBase = defesaBase;
        this.velocidade = velocidade;
        this.multiplicadorAtaque = multiplicadorAtaque;
        this.multiplicadorDefesa = multiplicadorDefesa;
        this.debuffDano = debuffDano;
    }

    public int receberDano(float danoPuro){
        
        float dano = danoPuro - ((Float)(getDefesaBase()*getMultiplicadorDefesa()));
        if(dano < 0 ) dano = 0f;
        setVidaAtual(getVidaAtual()-Math.round(dano));
        return Math.round(dano);
    }

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

    public boolean estaVivo(){
        if(getVidaAtual()<=0) setVivo(false);
        return vivo;
    }
    public Acao<String,Object> turnoNoCombate(){
        return null;
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
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
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
    public int getVelocidade() {
        return velocidade;
    }
    public float getDebuffDano() {
        return debuffDano;
    }
    public void setDebuffDano(float debuffDano) {
        this.debuffDano = debuffDano;
    }

}
