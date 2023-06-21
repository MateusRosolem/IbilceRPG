package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Pedagogo extends Inimigo {

    public Pedagogo(){
        super();
        setNome("Pedagogo");
        setTag("PEDAGOGO");
        setVidaMaxima(70);
        setVidaAtual(getVidaMaxima());
        setAtaqueBase(5);
        setDefesaBase(15);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(){
        if(getContadorTurnos()%3 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar();
        }else{
            incrementarContadorTurnos();
            return super.inimigoDefender();
        }

    }

}
