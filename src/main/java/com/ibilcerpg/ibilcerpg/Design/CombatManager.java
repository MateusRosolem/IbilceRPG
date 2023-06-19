package com.ibilcerpg.ibilcerpg.Design;

import com.ibilcerpg.ibilcerpg.Personagens.Biologo;
import com.ibilcerpg.ibilcerpg.Personagens.Inimigo;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;

public class CombatManager<T extends Inimigo>{
    public Player jogador;
    public Inimigo adversario;
    private boolean turno;
    private Acao<String,Object> acao;

    public CombatManager(Player jogador, T adversario) {
        this.jogador = jogador;
        this.adversario = adversario;
    }

    public Player getJogador() {
        return jogador;
    }

    public Inimigo getAdversario() {
        return adversario;
    }


    public void imprimirStatus(){
        System.out.println("Vida do jogador: " + jogador.getVidaAtual());
        System.out.println("Vida do adversario: " + adversario.getVidaAtual());
        System.out.println("--------------------------------------------------------------------------------------");

    }

    public void iniciarCombate(){
        getJogador().getInventario().getHabilidadeEquipada().reiniciarRecarga();
        if(jogador.getVelocidade() >= adversario.getVelocidade()){
            turno = true;
        }

        while(jogador.getVivo() && adversario.getVivo()){
            imprimirStatus();

            if(turno){
                jogador.ativarHabilidadePassiva();
                acao = jogador.turnoNoCombate();
                adversario.reacaoInimigo(acao);
                jogador.getInventario().getHabilidadeEquipada().decrementarRecarga();//diminui o tempo de
                // recarga da habilidada em 1
                turno = false;
            }else{
                acao = adversario.turnoNoCombate();
                jogador.reacaoJogador(acao);
                turno = true;
            }
        }
        jogador.desativarHabilidadePassiva();

        //SE COLOCAR A OPCAO DE CORRER, TEM QUE MUDAR ESSE IF TAMBEM!!!
        if(jogador.getVivo()){
            System.out.println("JOGADOR VENCEU!!!");
            jogador.receberExperiencia(adversario.getExpRecompensa());
        }else{
            System.out.println("Jogador foi eliminado.");
        }

        //retornar ao mapa
    }

    public void atacar(){

    }

}
