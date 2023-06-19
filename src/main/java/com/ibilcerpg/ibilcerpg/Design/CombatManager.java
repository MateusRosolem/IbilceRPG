package com.ibilcerpg.ibilcerpg.Design;


import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

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


    public void iniciarCombate() {
        jogador.getInventario().getHabilidadeEquipada().reiniciarRecarga();
        if (jogador.getVelocidade() >= adversario.getVelocidade()) {
            turno = true;
        }

        while (jogador.estaVivo() && adversario.estaVivo()) {
            System.out.println("Vida do jogador: " + jogador.getVidaAtual());
            System.out.println("Vida do adversario: " + adversario.getVidaAtual());
            System.out.println("--------------------------------------------------------------------------------------");

            while (jogador.estaVivo() && adversario.estaVivo()) {
                imprimirStatus();

                if (turno) {
                    jogador.ativarHabilidadePassiva();
                    acao = jogador.turnoNoCombate();
                    adversario.reacaoInimigo(acao);
                    jogador.getInventario().getHabilidadeEquipada().decrementarRecarga();//diminui o tempo de recarga da habilidada em 1
                    turno = false;
                } else {
                    acao = adversario.turnoNoCombate();
                    jogador.reacaoJogador(acao);
                    turno = true;
                }
            }
            jogador.desativarHabilidadePassiva();

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

            //retornar ao mapa
        }


//    public void atacar(){
//
//    }

    }
}
