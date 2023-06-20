package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Pedagogo extends Inimigo {

    public Pedagogo(){
        super();
        setNome("Pedagogo");
        setTag("PEDAGOGO");
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        return super.inimigoAtacar();
    }
}
