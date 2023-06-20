package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


public class Biologo extends Inimigo {

    public Biologo(){
        super();
        setNome("Biologo");
        setTag("BIOLOGO");
    }

    @Override
    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        return super.inimigoAtacar(UI);
    }
}
