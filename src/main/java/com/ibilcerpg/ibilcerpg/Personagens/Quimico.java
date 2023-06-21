package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;


public class Quimico extends Inimigo {

    private boolean acidoUsado;
    public Acao<String,Object> acidoCorrosivo(){
        setAcidoUsado(true);
        return (new Acao<String,Object>("ACIDO_CORROSIVO","DANO_ACIDO"));
    }

    public Quimico(){
        super();
        setNome("Químico");
        setTag("QUIMICO");
        setAcidoUsado(false);
    }



    public Acao<String,Object> turnoNoCombate(FXCombateController UI){
        if(getContadorTurnos()%3 == 0){
            incrementarContadorTurnos();
            return super.inimigoAtacar(UI);
        }else if(getContadorTurnos()%3 == 1){
            incrementarContadorTurnos();
            return super.inimigoDefender(UI);
        }else if(getContadorTurnos()%3 == 2){
                incrementarContadorTurnos();
                if(isAcidoUsado()){
                    return super.inimigoAtacar(UI);
                }else return acidoCorrosivo();
        }
        return null;
    }

    public boolean isAcidoUsado() {
        return acidoUsado;
    }
    public void setAcidoUsado(boolean acidoUsado) {
        this.acidoUsado = acidoUsado;
    }
}
