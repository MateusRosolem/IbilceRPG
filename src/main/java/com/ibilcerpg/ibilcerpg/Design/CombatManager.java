package com.ibilcerpg.ibilcerpg.Design;


import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;

public class CombatManager {
    private Player jogador;
    private Inimigo adversario;
    private boolean turno;
    private Acao<String, Object> acao;

    public CombatManager(Player jogador, Inimigo adversario) {
        this.jogador = jogador;
        this.adversario = adversario;
        iniciarCombate();
        novoTurno();
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

    private boolean verificarVivos(){
        if(jogador.estaVivo() && adversario.estaVivo()) return true;
        else return false;
    }

    public void novoTurno(/*FXCombateController UI*/){
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



            if(!verificarVivos()) finalizarCombate(/*UI*/);
            else novoTurno(/*UI*/);
    }

    private void finalizarCombate(/*FXCombateController */){
        if (jogador.estaVivo()) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("JOGADOR VENCEU!!!");
            jogador.getInventario().adicionarHabilidade(jogador.getMissoes().completarMissao(jogador.getMissoes().atualizarMissoes(adversario)));
            jogador.receberExperiencia(adversario.getExpRecompensa());
        } else {
            System.out.println("Jogador foi eliminado.");
            jogador.setVidaAtual(jogador.getVidaMaxima());
            jogador.setVivo(true);
        }
        jogador.setEfeitoNegativoPassivo("DEFAULT");
        jogador.setContadorTurnos(0);
//        try {
//            UI.terminate();
//        } catch (IOException e) {
//
//        }
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
