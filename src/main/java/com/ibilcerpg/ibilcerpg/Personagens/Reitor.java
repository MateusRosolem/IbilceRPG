package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Reitor extends Inimigo {

    public Reitor(){
        super();
        setNome("Biologo");
        setTag("BIOLOGO");
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        return super.inimigoAtacar();
    }
}
