package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Objetos.DentesDeVampiro;
import com.ibilcerpg.ibilcerpg.Objetos.ErvasMedicinais;
import com.ibilcerpg.ibilcerpg.Objetos.PedacoDePau;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import com.ibilcerpg.ibilcerpg.SuperClasses.QuestGiver;


public class Cozinheiro implements QuestGiver {
    public static Missao<DentesDeVampiro> missaoCozinheiro = new Missao<DentesDeVampiro>("MATEMATICO", new DentesDeVampiro(), "Missão do Phibonacci", 5, "Cozinheiro");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Essa noite ouvi alguns Engenheiros de Alimentos fazendo experimentos com a comida na dispensa do Restaurante Universitario, lide com 5 deles pra mim e eu te darei uma recompensa.");
        return missaoCozinheiro;
    }

}
