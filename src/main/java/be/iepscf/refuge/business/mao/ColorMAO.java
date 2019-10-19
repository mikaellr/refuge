package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.businessbean.Color;
import be.iepscf.refuge.business.businessbean.Species;

public interface ColorMAO extends GenericMAO<Color, Long>{

    public Color get(String name);
}
