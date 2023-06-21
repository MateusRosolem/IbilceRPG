package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
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



    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        return super.inimigoAtacar(UI);
    }


}
