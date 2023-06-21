package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Letreiro extends Inimigo {

    public Letreiro(){
        super();
        setNome("Letreiro");
        setTag("LETREIRO");
        setVidaMaxima(50);
        setAtaqueBase(7);
        setDefesaBase(5);
        setVidaAtual(50);
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        return super.inimigoAtacar();
    }


}
