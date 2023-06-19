package com.ibilcerpg.ibilcerpg.Design;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import java.util.ArrayList;

public class MissaoManager {
    ArrayList<Missao<? extends Habilidade>> missoes = new ArrayList<Missao<? extends Habilidade>>();

    public void adicionarMissao(Missao<? extends Habilidade> missao){
        missoes.add(missao);
    }
    
    public Missao<? extends Habilidade> atualizarMissoes(Inimigo inimigoDerrotado){
        for (Missao<? extends Habilidade> missao : missoes) {
            if(missao.getInimigo() == inimigoDerrotado.getTag()){
                missao.setN(missao.getN()-1);
                if(missao.done()) {
                    System.out.println("A missao " + missao.getNome() + " foi concluída! Retorne à quem a requisitou para coletar a recompensa!");
                    return missao;
                }

            }
        }
        return null;
    }

    public Habilidade completarMissao(Missao<? extends Habilidade> missaoConcluida){
        if(missaoConcluida != null) {
            if (missaoConcluida.done()) {
                System.out.println("Recompensa resgatada com sucesso! Você recebeu a habilidade " + missaoConcluida.getR().getNome());
                missoes.remove(missaoConcluida);
                return missaoConcluida.getR();
            }
        }
        return null;
    }
}
