package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
public class Habilidade extends Item {
    private Acao<String,Object> efeito;
    private int tempoDeRecarga;
    private int contadorRecarga;
    
    public void ativarRecarga(){
        setContadorRecarga(tempoDeRecarga+1);
    }

    public void reiniciarRecarga(){
        setContadorRecarga(0);
    }

    public void decrementarRecarga(){
        if(getContadorRecarga() > 0) setContadorRecarga(getContadorRecarga()-1);
    }

    public boolean checarTempoDeRecarga(){
        if(getContadorRecarga() <= 0){
            return true;
        }
        System.out.println("Ainda faltam " + getContadorRecarga() + " turnos para usar esta habilidade"); 
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
