package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;

/**
 * Classe pai de todas as habilidades especiais do jogo, nela tem todos os atributos e metodos usados pelas habilidades filhas (nao e uma habilidade em si)
 */
public class Habilidade extends Item implements Serializable {
    private Acao<String,Object> efeito;
    private int tempoDeRecarga;
    private int contadorRecarga;

    /**
     * Construtores
     */
    public Habilidade(){}

    public Habilidade(Acao<String, Object> efeito, int tempoDeRecarga, int contadorRecarga) {
        this.efeito = efeito;
        this.tempoDeRecarga = tempoDeRecarga;
        this.contadorRecarga = contadorRecarga;
    }

    /**
     * Ativa o tempo de recarga da habilidade, chamada toda vez que uma habilidade com tempo de recarga é usada.
     */
    public void ativarRecarga(){
        setContadorRecarga(tempoDeRecarga+1);
    }

    /**
     * Reinicia o tempo de recarga, chamada quando o combate se inicia
     */
    public void reiniciarRecarga(){
        setContadorRecarga(0);
    }

    /**
     * Decrementa em 1 o tempo de recarga da habilidade se já nao estiver em 0 (habilidade pronta)
     */
    public void decrementarRecarga(){
        if(getContadorRecarga() > 0) setContadorRecarga(getContadorRecarga()-1);
    }

    /**
     * Checa o tempo de recarga quando o jogador tenta usar a habilidade
     * @return retorna true se ela esta pronta e pode ser usada, se nao imprime uma mensagem de quantos turnos faltam para recarregar e retorna falso
     */
    public boolean checarTempoDeRecarga(FXCombateController UI){
        if(getContadorRecarga() <= 0){
            return true;
        }
        UI.imprimirTexto("Ainda faltam " + getContadorRecarga() + " turnos para usar esta habilidade");
        return false;
    }

    public Acao<String,Object> getEfeito(){
        return efeito;
    }

    public void setEfeito(Acao<String, Object> efeito) {
        this.efeito = efeito;
    }

    public int getTempoDeRecarga() {
        return tempoDeRecarga;
    }

    public void setTempoDeRecarga(int tempoDeRecarga) {
        this.tempoDeRecarga = tempoDeRecarga;
    }

    public int getContadorRecarga() {
        return contadorRecarga;
    }

    public void setContadorRecarga(int contadorRecarga) {
        this.contadorRecarga = contadorRecarga;
    }
}
