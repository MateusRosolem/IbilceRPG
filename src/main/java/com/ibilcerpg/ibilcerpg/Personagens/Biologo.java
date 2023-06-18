package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;

public class Biologo extends Inimigo {
    
    public Biologo(){
        super();
        setNome("Biologo");
    }



    @Override
    public Acao<String,Object> turnoNoCombate(){
        System.out.println("Turno do Biologo");
        return super.inimigoAtacar();
    }
}
