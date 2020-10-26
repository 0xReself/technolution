package com.github.technolution.technolution.objects.tileentity.eta;

import com.github.technolution.technolution.init.Register;
import com.github.technolution.technolution.objects.tileentity.EnergyAbsorberEntity;

public class EnergyAbsorberEntityEta extends EnergyAbsorberEntity {

    public EnergyAbsorberEntityEta() {
        super(Register.ENERGY_ABSORBER_ENTITY_ETA.get());
        this.blockTier = 3;
    }
    
}
