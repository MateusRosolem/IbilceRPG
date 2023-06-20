package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Letreiro extends Inimigo {

    public Letreiro(){
        super();
        setNome("Biologo");
        setTag("BIOLOGO");
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        return super.inimigoAtacar();
    }


}
