package com.ibilcerpg.ibilcerpg.SuperClasses;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Design.MissaoManager;
import com.ibilcerpg.ibilcerpg.Objetos.HabilidadeDefault;

/**
 * Interface para todos os Personagens que darão missoes ao jogador
 */
public interface QuestGiver {
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa){return null;}

}
