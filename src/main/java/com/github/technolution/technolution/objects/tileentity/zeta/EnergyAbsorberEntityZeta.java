package com.github.technolution.technolution.objects.tileentity.zeta;

import com.github.technolution.technolution.init.Register;
import com.github.technolution.technolution.objects.tileentity.EnergyAbsorberEntity;

public class EnergyAbsorberEntityZeta extends EnergyAbsorberEntity {

    public EnergyAbsorberEntityZeta() {
        super(Register.ENERGY_ABSORBER_ENTITY_ZETA.get());
        this.blockTier = 4;
    }
}
