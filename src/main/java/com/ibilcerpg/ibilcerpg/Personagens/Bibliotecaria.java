package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXMapaController;
import com.ibilcerpg.ibilcerpg.Design.Missao;
import com.ibilcerpg.ibilcerpg.Objetos.DentesDeVampiro;
import com.ibilcerpg.ibilcerpg.Objetos.ErvasMedicinais;
import com.ibilcerpg.ibilcerpg.Objetos.PedacoDePau;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import com.ibilcerpg.ibilcerpg.SuperClasses.QuestGiver;


public class Bibliotecaria implements QuestGiver {
    public static Missao<PedacoDePau> missaoBibliotecaria = new Missao<PedacoDePau>("TRADUTOR", new PedacoDePau(), "Missão do Tradutor que roubava livros", 6, "Bibliotecaria");
    public static Missao<? extends Habilidade> darMissao(FXMapaController UIMapa) {
        UIMapa.imprimirTexto("Alguns Tradutores estão tentando roubar livros da biblioteca, lide com 6 deles pra mim e eu te darei uma recompensa");
        return missaoBibliotecaria;
    }
}
