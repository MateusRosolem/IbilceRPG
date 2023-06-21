package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


public class Traduteiro extends Inimigo {

    public Traduteiro(){
        super();
        setNome("Traduteiro");
        setTag("TRADUTOR");
        setVidaMaxima(35);
        setVidaAtual(getVidaMaxima());
        setAtaqueBase(8);
        setDefesaBase(3);
    }

    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        if(getContadorTurnos()%2 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar(UI);
        }else{
            incrementarContadorTurnos();
            return super.inimigoDefender(UI);
        }
        
    }

    
}
