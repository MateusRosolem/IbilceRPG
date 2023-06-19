package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

public class PedacoDePau extends Habilidade{//habilidade exemplo
    
    public PedacoDePau(){
        setNome("Pedaço de Pau");
        setContadorRecarga(0);
        setTempoDeRecarga(3);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("DANO");
        getEfeito().setV(15f);
    }
    
}
