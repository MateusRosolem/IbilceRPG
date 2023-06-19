package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


public class Alejandro extends Inimigo {
    public Missao<ErvasMedicinais> primeiraMissao = new Missao<ErvasMedicinais>("BIOLOGO", new ErvasMedicinais(), "Missão do Arboreto", 3, "Alejandro");
    public Missao<PedacoDePau> segundaMissao = new Missao<PedacoDePau>("TRADUTOR", new PedacoDePau(), "Missão do Inglês", 3, "Alejandro");
    public Missao<DentesDeVampiro> terceiraMissao = new Missao<DentesDeVampiro>("MATEMATICO", new DentesDeVampiro(), "Missão do Phibonacci", 3, "Alejandro");


}
