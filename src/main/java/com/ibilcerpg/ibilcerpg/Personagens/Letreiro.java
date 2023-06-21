package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Letreiro extends Inimigo {

    public Letreiro(){
        super();
        setNome("Biologo");
        setTag("BIOLOGO");
    }



    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        return super.inimigoAtacar(UI);
    }


}
