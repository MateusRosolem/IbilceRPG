package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


public class Traduteiro extends Inimigo {

    public Traduteiro(){
        super();
        setNome("Traduteiro");
        setTag("TRADUTOR");
        setAtaqueBase(8);
        setDefesaBase(3);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(){
        if(getContadorTurnos()%2 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar();
        }else{
            incrementarContadorTurnos();
            return super.inimigoDefender();
        }
        
    }

    
}
