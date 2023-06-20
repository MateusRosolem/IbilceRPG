package com.ibilcerpg.ibilcerpg.Design;


import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;

public class CombatManager<T extends Inimigo>{
    private Player jogador;
    private T adversario;
    private boolean turno;
    private Acao<String, Object> acao;

    public CombatManager(Player jogador, T adversario) {
        this.jogador = jogador;
        this.adversario = adversario;
        iniciarCombate();
    }

    public Player getJogador() {
        return jogador;
    }

    public T getAdversario() {
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
        return false;
    }

    public void novoTurno(FXCombateController UI){
        imprimirStatus();


        //turno jogador
        UI.imprimirTexto("Turno do " + jogador.getNome());

        jogador.ativarHabilidadePassiva();
        acao = jogador.turnoNoCombate();
        UI.imprimirTexto(acao.getT()+"\n");
        adversario.reacaoInimigo(acao);
        UI.inimigoVidaProgressBarUpdate();
        jogador.getInventario().getHabilidadeEquipada().decrementarRecarga();//diminui o tempo de recarga da habilidada em 1
        jogador.incrementarContadorTurnos();
        UI.imprimirStatus();
        UI.imprimirTexto(UI.caixaDeTexto.getText() + "Vida do jogador: " + jogador.getVidaAtual() + "\nVida do " +
                "adversario" + adversario.getVidaAtual());

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

        if(!verificarVivos()){
            finalizarCombate(UI);
            return;
        }
        UI.imprimirTexto("Turno do " + adversario.getNome());
        //turno adversario
        System.out.println("Turno do " + adversario.getNome());
        acao = adversario.turnoNoCombate();
        jogador.reacaoJogador(acao);
        UI.playerVidaProgressBarUpdate();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        UI.imprimirTexto("Turno do " + jogador.getNome());

        if(!verificarVivos()){
            finalizarCombate(UI);
            return;
        }
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
