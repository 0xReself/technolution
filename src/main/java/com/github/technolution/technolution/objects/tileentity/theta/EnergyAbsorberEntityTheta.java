package com.github.technolution.technolution.objects.tileentity.theta;

import com.github.technolution.technolution.init.Register;
import com.github.technolution.technolution.objects.tileentity.EnergyAbsorberEntity;

public class EnergyAbsorberEntityTheta extends EnergyAbsorberEntity {

    public EnergyAbsorberEntityTheta() {
        super(Register.ENERGY_ABSORBER_ENTITY_THETA.get());
        this.blockTier = 2;
    }
}
