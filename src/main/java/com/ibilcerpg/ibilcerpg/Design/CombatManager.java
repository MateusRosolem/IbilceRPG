package com.ibilcerpg.ibilcerpg.Design;


import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CombatManager {
    private Player jogador;
    private Inimigo adversario;
    private boolean turno;
    private Acao<String, Object> acao;

    public CombatManager(Player jogador, Inimigo adversario) {
        this.jogador = jogador;
        this.adversario = adversario;
        iniciarCombate();
    }

    public Player getJogador() {
        return jogador;
    }

    public Inimigo getAdversario() {
        return adversario;
    }

    public boolean getTurno(){ return turno;}

    public void imprimirStatus(FXCombateController UI) {
        UI.imprimirStatus();
        UI.imprimirStatus( "------------------");
        UI.imprimirStatus( "Vida do adversario: " + adversario.getVidaAtual());
    }

    public boolean verificarVivos(){
        if(jogador.estaVivo() && adversario.estaVivo()) return true;
        else return false;
    }

    public void novoTurno(FXCombateController UI,int acaoTipo){
        imprimirStatus(UI);

        //turno jogador
        jogador.ativarEfeitosPassivos(UI);

        switch (acaoTipo) {
            case 1:acao = jogador.jogadorAtacar(UI);break;
            case 2:acao = jogador.jogadorDefender(UI);break;
            case 3:acao = jogador.jogadorHabilidade(UI);break;
            case 4:acao = jogador.jogadorItem(UI);break;
        }

        adversario.reacaoInimigo(acao);
        UI.inimigoVidaProgressBarUpdate();
        jogador.getInventario().getHabilidadeEquipada().decrementarRecarga();//diminui o tempo de recarga da habilidada em 1
        jogador.incrementarContadorTurnos();
        imprimirStatus(UI);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //turno adversario
        UI.imprimirTexto("Turno do " + adversario.getNome());
        acao = adversario.turnoNoCombate();
        jogador.reacaoJogador(acao);
        UI.playerVidaProgressBarUpdate();



        if(!verificarVivos()) finalizarCombate(UI);
        return;
    }

    private void finalizarCombate(FXCombateController UI){
        if (jogador.estaVivo()) {
            UI.imprimirTexto("--------------------------------------------------------------------------------------\n"+
                            "JOGADOR VENCEU!!!");
            jogador.getInventario().adicionarHabilidade(jogador.getMissoes().completarMissao(jogador.getMissoes().atualizarMissoes(adversario)));
            jogador.receberExperiencia(adversario.getExpRecompensa());
            jogador.checarProgresso(adversario);
        } else {
            UI.imprimirTexto("Jogador foi eliminado.");
            jogador.setVidaAtual(jogador.getVidaMaxima());
            jogador.setVivo(true);
        }
        jogador.setEfeitoNegativoPassivo("DEFAULT");
        jogador.setContadorTurnos(0);

    }


    public void iniciarCombate() {
        jogador.getInventario().getHabilidadeEquipada().reiniciarRecarga();
        jogador.setItemDisponivel(true);
    }


    public Acao<String, Object> getAcao() {
        return acao;
    }

    public void setAcao(Acao<String, Object> acao) {
        this.acao = acao;
    }
}