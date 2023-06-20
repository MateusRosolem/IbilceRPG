package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;

public class Fisico extends Inimigo {
    private float buffProximoDano;

    private boolean Carregando;

    private Acao<String,Object> AcaoEReacao(FXCombateController UI){
        if(buffProximoDano != 0 ) UI.imprimirTexto("Ataque Poderoso!");
        setCarregando(false);
        return inimigoAtacar(buffProximoDano,UI);
    }

    private Acao<String,Object> CarregarAcaoEReacao(FXCombateController UI){
        UI.imprimirTexto("Fisico não reagiu");
        setCarregando(true);
        return new Acao<String,Object>("DEFAULT","DEFAULT");
    }


    public Fisico(){
        super();
        setTag("FISICO");
        setNome("Físico");
        setDefesaBase(5);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        if(getContadorTurnos()%4 == 0 || getContadorTurnos()%4 == 1){
            incrementarContadorTurnos();
            return inimigoAtacar(UI);
        }else if(getContadorTurnos()%4 == 2){
            incrementarContadorTurnos();
            return CarregarAcaoEReacao(UI);
        }else if(getContadorTurnos()%4 == 3){
            incrementarContadorTurnos();
            return AcaoEReacao(UI);
        }
        return null;
    }

    public Acao<String,Object> inimigoAtacar(float buff,FXCombateController UI){
        Acao<String,Object> turno = new Acao<String,Object>();
        turno.setT("ATAQUE");
        turno.setV(buff);
        setDebuffDano(1);
        setBuffProximoDano(0);
        UI.imprimirTexto(turno.getT());
        return turno;
    }

    @Override
    public int receberDano(float danoPuro, FXCombateController UI){
        if(isCarregando()) setBuffProximoDano(Math.round(danoPuro)*2);
        return super.receberDano(danoPuro,UI);
    }

    public float getBuffProximoDano() {
        return buffProximoDano;
    }

    public void setBuffProximoDano(float buffProximoDano) {
        this.buffProximoDano = buffProximoDano;
    }

    public boolean isCarregando() {
        return Carregando;
    }

    public void setCarregando(boolean carregando) {
        Carregando = carregando;
    }


}
