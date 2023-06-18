package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;

public class Traduteiro extends Inimigo {

    public Traduteiro(){
        super();
        setNome("Traduteiro");
        setAtaqueBase(8);
        setDefesaBase(3);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(){
        System.out.println("Turno do Tradutor");
        if(getContadorTurnos()%2 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar();
        }else{
            incrementarContadorTurnos();
            return super.inimigoDefender();
        }
        
    }

    
}
