package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Objetos.PedacoDePau;
import com.ibilcerpg.ibilcerpg.Objetos.Vuvuzela;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import com.ibilcerpg.ibilcerpg.SuperClasses.QuestGiver;


public class Entorpecida implements QuestGiver {
    public static Missao<Vuvuzela> missaoEntorpecida = new Missao<Vuvuzela>("PEDAGOGO", new Vuvuzela(), "Missão do anti CAASO", 2, "Entorpecida");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Nós da torcida organizada não podemos torcer para os cursos pois precisamos ser inclusivos, mas temos o dever de torcer para qualquer computação que não seja da USP São Carlos, então derrote 2 pedagogos e torceremos por você!");
        return missaoEntorpecida;
    }
}
