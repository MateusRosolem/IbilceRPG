package com.ibilcerpg.ibilcerpg.Objetos;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

/**
 * Habilidade que cura uma pequena quantidade de vida do jogador todo turno
 */
public class Vuvuzela extends Habilidade{

    public Vuvuzela(){
        setNome("Vuvuzela");
        setContadorRecarga(0);
        setTempoDeRecarga(0);
        setEfeito(new Acao<String,Object>());
        getEfeito().setT("PASSIVA");
        getEfeito().setV("AUMENTAR_DANO");
    }
}