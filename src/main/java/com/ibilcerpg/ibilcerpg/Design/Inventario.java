package com.ibilcerpg.ibilcerpg.Design;

import com.ibilcerpg.ibilcerpg.Objetos.DentesDeVampiro;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;

public class Inventario {
    private Habilidade habilidadeEquipada;

    public Habilidade getHabilidadeEquipada() {
        return habilidadeEquipada;
    }

    public Inventario(){
        habilidadeEquipada = new DentesDeVampiro();
    }


}