package com.ibilcerpg.ibilcerpg.Design;


import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Classe que controla todos os combates, recebe o Jogador e o adversario no construtor
 */
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


    public void iniciarCombate() {
        jogador.getInventario().getHabilidadeEquipada().reiniciarRecarga();
        jogador.setItemDisponivel(true);
    }


    public void imprimirStatus(FXCombateController UI) {
        UI.imprimirStatus();
        UI.imprimirStatus( "------------------");
        UI.imprimirStatus( "Vida do adversario: " + adversario.getVidaAtual());
    }



    /**
     * Metodo que verifica se algum dos combatentes morreu
     * @return returna true se ainda estao vivos e false se algum deles morreu
     */
    private boolean verificarVivos(){

        if(jogador.estaVivo() && adversario.estaVivo()) return true;
        else return false;
    }


    public void novoTurno(FXCombateController UI,int acaoTipo) {
        imprimirStatus(UI);

        //turno jogador
        jogador.ativarEfeitosPassivos(UI);

        switch (acaoTipo) {
            case 1:
                acao = jogador.jogadorAtacar(UI);
                break;
            case 2:
                acao = jogador.jogadorDefender(UI);
                break;
            case 3:
                acao = jogador.jogadorHabilidade(UI);
                break;
            case 4:
                acao = jogador.jogadorItem(UI);
                break;
        }

        adversario.reacaoInimigo(acao);
        UI.inimigoVidaProgressBarUpdate();
        jogador.getInventario().getHabilidadeEquipada().decrementarRecarga();//diminui o tempo de recarga da habilidada em 1
        jogador.incrementarContadorTurnos();
        imprimirStatus(UI);
    }
    /**
     * Metodo principal do combate, é chamado toda vez que o jogador realiza uma acao valida pelos botoes de combate
     * to.do novo turno consiste em uma acao do jogador e uma acao do adversario, que quando termina espera o
     * novo input do jogador.
     * Esse metodo chama os metodos que, em ordem: ativa os efeitos passivos do jogador, realiza a reacao do inimigo
     * referente a acao do jogador, decrementa a recarga da habilidade do jogador, incrementa o contador de turnos do
     * jogador, chama o turno do adversario, reacao do jogador a acao do adversario, verifica se algum dos membros foi
     * derrutado, e se sim, finaliza o combate, se nao, espera o proximo turno.
     *  //@param UI recebe o elemento da interface do combate,que atualiza as barras de vida e a caixa de texto depois de toda ação
     */
    public void novoTurno(FXCombateController UI){
        imprimirStatus(UI);

            //turno jogador
            jogador.ativarEfeitosPassivos(UI);
            acao = jogador.turnoNoCombate();
            adversario.reacaoInimigo(acao);
            //UI.vidaProgressBarUpdate(UI.playerVidaProgresBar);
            //UI.vidaProgressBarUpdate(UI.vidaProgressBar);
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



    /**
     * Metodo que finaliza o combate, é chamado assim que algum dos integrantes for derrotado, o método atualiza a missao do
     * jogador referente ao adversario derrotado se for cabivel, dá ao jogador a experiencia do adversario e atualiza
     * quais cursos o jogador ja derrotou com o adversario derrotado.
     * se o jogador perdeu, ele é apenas revivido
     * de qualquer modo o efeito passivo do jogador volta para DEFAULT e seu contador de turnos é resetado, além de voltar
     * para o mapa.
     */
    public void finalizarCombate(FXCombateController UI){

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


    /**
     * Inicia o combate com metodos que so precisam ser chamados no começo do combate, como reiniciar a recarga da habilidade
     * e deixar o item do jogador disponivel para uso.
     */



    //getters e setters
    public Acao<String, Object> getAcao() {
        return acao;
    }
    public void setAcao(Acao<String, Object> acao) {
        this.acao = acao;
    }



    public Player getJogador() {
        return jogador;
    }
    public Inimigo getAdversario() {
        return adversario;
    }
    public boolean getTurno(){ return turno;}
}

