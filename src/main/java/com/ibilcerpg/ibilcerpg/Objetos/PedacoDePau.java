package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

/**
 * Habilidade que causa dano maior que o ataque
 */
public class PedacoDePau extends Habilidade{
    
    public PedacoDePau(){
        setNome("Pedaço de Pau");
        setContadorRecarga(0);
        setTempoDeRecarga(4);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("DANO");
        getEfeito().setV(null);
    }
    
}
