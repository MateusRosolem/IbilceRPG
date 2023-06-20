package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;

/**
 * Classe generica que armazena uma acao no combate, tanto do jogador quanto do adversario
 *
 * @param <T> normalmente sinaliza uma String que descreve a acao
 * @param <V> pode sinalizar um valor da acao descrita, outra acao quando o turno é mais complexo ou apenas um efeito de status
 */
public class Acao<T,V> implements Serializable {
    private T t;
    private V v;

    public Acao(T t, V v) {
        this.t = t;
        this.v = v;
    }

    public Acao(){};

    
    public void setT(T t){
        this.t = t;
    }
    public void setV(V v){
        this.v = v;
    }

    public T getT(){
        return t;
    }

    public V getV(){
        return v;
    }
}