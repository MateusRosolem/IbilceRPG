package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Objetos.PedacoDePau;
import com.ibilcerpg.ibilcerpg.Objetos.RedeDeVolei;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import com.ibilcerpg.ibilcerpg.SuperClasses.QuestGiver;


public class Atletica implements QuestGiver {
    public static Missao<RedeDeVolei> missaoAtletica = new Missao<RedeDeVolei>("QUIMICO", new RedeDeVolei(), "Missão do Intercursos", 4, "Atlética");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Você! Da computação! Preciso da sua ajuda! 4 membros da Química estão exigindo o prêmio de primeiro lugar do Intercursos mesmo não tendo ganhado! Me ajude a lidar com eles e te darei uma recompensa!");
        return missaoAtletica;
    }
}
