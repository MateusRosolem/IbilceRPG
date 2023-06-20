package com.ibilcerpg.ibilcerpg.Design;
import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.Controllers.FXInventarioController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que cuida das missoes coletadas pelo jogador, permitindo completá-las e receber recompensas por elas
 */
public class MissaoManager implements Serializable {
    ArrayList<Missao<? extends Habilidade>> missoes = new ArrayList<Missao<? extends Habilidade>>();

    /**
     * Adiciona uma nova missao a lista de missoes
     * @param missao nova missao a ser adicionada
     */
    public void adicionarMissao(Missao<? extends Habilidade> missao){
        for (Missao<? extends Habilidade> quest: missoes) {
            if(missao.getNome() == quest.getNome()){
                return;
            }
        }
        missoes.add(missao);
    }

    /**
     * Acha a missao na lista de missoes que corresponde ao inimigo derrotado
     * @param inimigoDerrotado checa a missao do inimigo que foi derrotado para ver se ela foi concluida
     * @return retorna ela caso tenha sido concluida (para ser utilizada como parametro da funcao completarMissao()
     */
    public Missao<? extends Habilidade> atualizarMissoes(Inimigo inimigoDerrotado, FXCombateController UI){
        for (Missao<? extends Habilidade> missao : missoes) {
            if(missao.getInimigo() == inimigoDerrotado.getTag()){
                missao.setN(missao.getN()-1);
                if(missao.done()) {
                    UI.imprimirTexto("A missao: " + missao.getNome() + " foi concluída!");
                    return missao;
                }
            }
        }
        return null;
    }

    /**
     * Metodo que retorna a habilidade de recompensa da missao concluida, é chamada quando o jogador retorna a quem requisitou a missao
     * @param missaoConcluida Recebe a missao que o jogador acabou de concluir
     * @return retorna a habilidade como recompensa da missao
     */
    public Habilidade completarMissao(Missao<? extends Habilidade> missaoConcluida,FXCombateController UI){
        if(missaoConcluida != null) {
            if (missaoConcluida.done()) {
                UI.imprimirTexto("Recompensa resgatada com sucesso! Você recebeu a habilidade " + missaoConcluida.getR().getNome());
                missaoConcluida.setConcluida(true);
                return missaoConcluida.getR();
            }
        }
        return null;
    }

    /**
     *
     * @param UIinventario
     */
    public void displayMissoes(FXInventarioController UIinventario){
        String missaos = "";
        int i = 1;
        for (Missao<? extends Habilidade> missao: missoes) {
            missaos = missaos + "\n"+ i + ": " + missao.getNome();
            i++;
        }
        UIinventario.descricaoItem.setText(missaos);
    }
}
