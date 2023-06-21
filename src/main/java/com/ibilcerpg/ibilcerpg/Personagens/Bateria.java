package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Objetos.PedacoDePau;
import com.ibilcerpg.ibilcerpg.Objetos.Surdo;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import com.ibilcerpg.ibilcerpg.SuperClasses.QuestGiver;


public class Bateria implements QuestGiver {
    public static Missao<Surdo> missaoBateria = new Missao<Surdo>("MATEMATICO", new Surdo(), "Missão do Inglês", 6, "Bateria");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Você é da computação não é? Preciso de alguem que espante esses Matemáticos que estão atrapalhando o ensaio, acabar com uns 6 deles já deve dar, faça isso e te darei uma recompensa.");
        return missaoBateria;
    }
}
