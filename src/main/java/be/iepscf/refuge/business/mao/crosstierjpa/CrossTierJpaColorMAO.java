package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Color;
import be.iepscf.refuge.business.mao.ColorMAO;

import java.util.List;

public class CrossTierJpaColorMAO extends CrossTierJpaGenericMAO<Color, Long> implements ColorMAO {

    @Override
    public Color get(String name) {
        return conv(getBeanService().getColorByName(name));
    }

    @Override
    public Color get(Long id) {
        return conv(getBeanService().getColor(id));
    }

    @Override
    public List<Color> get() {
        return convColors(getBeanService().getColors());
    }

    @Override
    public long save(Color entity) {
        return getBeanService().saveColor(conv(entity));
    }

    @Override
    public long update(Color entity) {
        return getBeanService().updateColor((conv(entity)));
    }

    @Override
    public long delete(Color entity) {
        return getBeanService().deleteColor((conv(entity)));
    }

}
