package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

public class HabilidadeDefault extends Habilidade{

    public HabilidadeDefault(){
        setNome("Default");
        setContadorRecarga(0);
        setTempoDeRecarga(0);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("DEFAULT");
        getEfeito().setV("DEFAULT");
    }
}