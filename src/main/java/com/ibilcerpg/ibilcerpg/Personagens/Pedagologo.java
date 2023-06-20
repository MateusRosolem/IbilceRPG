package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Pedagologo extends Inimigo {

    public Pedagologo(){
        super();
        setNome("Biologo");
        setTag("BIOLOGO");
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        return super.inimigoAtacar();
    }
}
