package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
public class Acao<T,V>{
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