package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

/**
 * Habilidade que cura uma pequena quantidade de vida do jogador todo turno
 */
public class PensamentoPositivo extends Habilidade{

    public PensamentoPositivo(){
        setNome("Pensamentos Positivos");
        setContadorRecarga(0);
        setTempoDeRecarga(0);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("PASSIVA");
        getEfeito().setV("AUMENTAR_DEFESA");
    }
}