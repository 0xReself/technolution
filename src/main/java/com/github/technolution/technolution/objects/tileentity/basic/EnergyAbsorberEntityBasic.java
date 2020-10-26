package com.github.technolution.technolution.objects.tileentity.basic;

import com.github.technolution.technolution.init.Register;
import com.github.technolution.technolution.objects.tileentity.EnergyAbsorberEntity;

public class EnergyAbsorberEntityBasic extends EnergyAbsorberEntity {

    public EnergyAbsorberEntityBasic() {
        super(Register.ENERGY_ABSORBER_ENTITY_BASIC.get());
        this.blockTier = 1;
    }
    
}
