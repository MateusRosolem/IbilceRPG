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

    public void imprimirStatus() {
        System.out.println("Vida do jogador: " + jogador.getVidaAtual());
        System.out.println("Vida do adversario: " + adversario.getVidaAtual());
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public boolean verificarVivos(){
        if(jogador.estaVivo() && adversario.estaVivo()) return true;
        else return false;
    }

    public void novoTurno(FXCombateController UI){
        imprimirStatus();

        //turno jogador
        jogador.ativarEfeitosPassivos();
        acao = jogador.turnoNoCombate();
        adversario.reacaoInimigo(acao);
        //UI.vidaProgressBarUpdate(UI.vidaProgressBar);
        jogador.getInventario().getHabilidadeEquipada().decrementarRecarga();//diminui o tempo de recarga da habilidada em 1
        jogador.incrementarContadorTurnos();
        imprimirStatus();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //turno adversario
        System.out.println("Turno do " + adversario.getNome());
        acao = adversario.turnoNoCombate();
        jogador.reacaoJogador(acao);
        //UI.vidaProgressBarUpdate(UI.playerVidaProgresBar);



        if(!verificarVivos()) finalizarCombate(UI);
        return;
    }

    private void finalizarCombate(FXCombateController UI){
        if (jogador.estaVivo()) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("JOGADOR VENCEU!!!");
            jogador.getInventario().adicionarHabilidade(jogador.getMissoes().completarMissao(jogador.getMissoes().atualizarMissoes(adversario)));
            jogador.receberExperiencia(adversario.getExpRecompensa());
            jogador.checarProgresso(adversario);
        } else {
            System.out.println("Jogador foi eliminado.");
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