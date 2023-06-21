package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class EngenheiroDeAlimentos extends Inimigo {

    public EngenheiroDeAlimentos(){
        super();
        setNome("Engenheiro de Alimentos");
        setTag("ENGENHEIRO");
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
