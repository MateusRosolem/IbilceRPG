package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Objetos.PedacoDePau;
import com.ibilcerpg.ibilcerpg.Objetos.PensamentoPositivo;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import com.ibilcerpg.ibilcerpg.SuperClasses.QuestGiver;


public class DAF implements QuestGiver {
    public static Missao<PensamentoPositivo> missaoDAF = new Missao<PensamentoPositivo>("LETREIRO", new PensamentoPositivo(), "Missão do Amargurado", 3, "DAF");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Essa situação do nosso campus é realmente deplorável, me faz pensar, é uma boa oportunidade de acabar com aqueles 'Letras bacharel' de nariz empinado, derrote 3 deles pra mim e te darei algo sem preço. ");
        return missaoDAF;
    }
}
