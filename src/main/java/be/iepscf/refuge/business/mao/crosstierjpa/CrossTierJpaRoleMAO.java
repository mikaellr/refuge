package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.mao.RoleMAO;

import java.util.List;

public class CrossTierJpaRoleMAO extends CrossTierJpaGenericMAO<Role, Long> implements RoleMAO {

    @Override
    public Role get(Long id) {
        return conv(getBeanService().getRole(id));
    }

    @Override
    public List<Role> get() {
        return convRoles(getBeanService().getRoles());
    }

    @Override
    public long save(Role entity) {
        return getBeanService().saveRole(conv(entity));
    }

    @Override
    public long update(Role entity) {
        return getBeanService().updateRole(conv(entity));
    }

    @Override
    public long delete(Role entity) {
        return getBeanService().deleteRole(conv(entity));
    }

}
