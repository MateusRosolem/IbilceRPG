package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


/**
 * Adversario e questgiver
 */
public class Alejandro extends Inimigo implements QuestGiver{

    static int dialogoProgresso;
    public static Missao<ErvasMedicinais> missaoArboreto = new Missao<ErvasMedicinais>("BIOLOGO", new ErvasMedicinais(), "Missão do Arboreto", 3, "Alejandro");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Agora quero ver do que você é capaz, tem um grupo de Biólogos se escondendo no arboreto e estão chegando perto demais desse prédio pro meu gosto, lide com 3 deles e venha falar comigo");
        return missaoArboreto;
    }

    public static void dialogo1(FXMapaController UIMapa){

    }
    public Alejandro(){
        super();
        setNome("Alejandro");
        setTag("ALEJANDRO");
        setAtaqueBase(12);
        setDefesaBase(12);
        setVidaMaxima(100);
        setVidaAtual(getVidaMaxima());
    }

    @Override
    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        if(getContadorTurnos()%2 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar(UI);
        }else{
            incrementarContadorTurnos();
            return super.inimigoDefender(UI);
        }

    }
}
