package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Pedagogo extends Inimigo {

    public Pedagogo(){
        super();
        setNome("Pedagogo");
        setTag("PEDAGOGO");
    }



    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        return super.inimigoAtacar(UI);
    }
}
