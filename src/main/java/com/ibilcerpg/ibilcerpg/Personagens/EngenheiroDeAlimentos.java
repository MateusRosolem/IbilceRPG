package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class EngenheiroDeAlimentos extends Inimigo {

    public EngenheiroDeAlimentos(){
        super();
        setNome("Engenheiro de Alimentos");
        setTag("ENGENHEIRO");
        setAtaqueBase(8);
        setDefesaBase(3);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(){
        System.out.println("Turno do Engenheiro");
        if(getContadorTurnos()%2 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar();
        }else{
            incrementarContadorTurnos();
            return super.inimigoDefender();
        }
        
    }

    
}
