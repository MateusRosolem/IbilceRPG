package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Reitor extends Inimigo {

    public Reitor(){
        super();
        setNome("Reitor");
        setTag("REITOR");
        setAtaqueBase(20);
        setDefesaBase(20);
        setVidaMaxima(200);
        setVidaAtual(getVidaMaxima());
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        return super.inimigoAtacar();
    }
}
