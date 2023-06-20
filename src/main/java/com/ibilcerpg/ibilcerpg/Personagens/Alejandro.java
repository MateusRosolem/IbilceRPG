package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


/**
 * Adversario e questgiver
 */
public class Alejandro extends Inimigo implements QuestGiver{

    static int jogador_progresso;
    public static Missao<ErvasMedicinais> missaoArboreto = new Missao<ErvasMedicinais>("BIOLOGO", new ErvasMedicinais(), "Missão do Arboreto", 3, "Alejandro");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Agora quero ver do que você é capaz, tem um grupo de Biólogos se escondendo no arboreto e estão chegando perto demais desse prédio pro meu gosto, lide com 3 deles e venha falar comigo");
        return missaoArboreto;
    }


}
