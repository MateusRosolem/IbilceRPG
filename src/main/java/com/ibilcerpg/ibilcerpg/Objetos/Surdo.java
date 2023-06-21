package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

/**
 * Habilidade que causa dano maior que o ataque
 */
public class Surdo extends Habilidade{

    public Surdo(){
        setNome("Surdo");
        setContadorRecarga(0);
        setTempoDeRecarga(0);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("REDUCAO_DE_DEFESA");
        getEfeito().setV(0.5f);
    }
    
}
