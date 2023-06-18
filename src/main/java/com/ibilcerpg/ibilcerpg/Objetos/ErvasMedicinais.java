package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

public class ErvasMedicinais extends Habilidade {

    public ErvasMedicinais(){
        setNome("Ervas Medicinais");
        setContadorRecarga(0);
        setTempoDeRecarga(0);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("PASSIVA");
        getEfeito().setV("CURA");
    }
}