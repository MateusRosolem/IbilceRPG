package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
public class Matematico extends Inimigo {
    private int danoAnterior;
    private int aux;

    private int proximoDano(){//phibonacci
        if(getDanoAnterior() == 0) return 1;
        return getDanoAnterior() + getAux();
    }


    public Matematico(){
        super();
        setAux(0);
        setTag("MATEMATICO");
        setDanoAnterior(0);
        setNome("Matematico");
        setDefesaBase(6);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        return inimigoAtacar(UI);
    }

    @Override
    public Acao<String,Object> inimigoAtacar(FXCombateController UI){
        int dano = proximoDano();
        setAux(danoAnterior);
        setDanoAnterior(dano);
        UI.imprimirTexto("ATAQUE");
        return new Acao<String,Object>("DANO_REAL",dano);
    }





    public int getDanoAnterior() {
        return danoAnterior;
    }


    public void setDanoAnterior(int danoAnterior) {
        this.danoAnterior = danoAnterior;
    }


    public int getAux() {
        return aux;
    }


    public void setAux(int aux) {
        this.aux = aux;
    }



    
}
