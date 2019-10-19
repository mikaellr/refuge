package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.mao.UserMAO;

import java.util.List;

public class CrossTierJpaUserMAO extends CrossTierJpaGenericMAO<User, Long> implements UserMAO {

    @Override
    public User getByEmail(String email) {
        return  conv(getBeanService().getUserByEmail(email));
    }

    @Override
    public User get(Long id) {
        return conv(getBeanService().getUser(id));
    }

    @Override
    public List<User> get() {
        return convUsers(getBeanService().getUsers());
    }


    @Override
    public long save(User item) {
        return getBeanService().saveUser(conv(item));
    }

    @Override
    public long update(User item) {
        return getBeanService().updateUser(conv(item));
    }

    @Override
    public long delete(User item) {
        return getBeanService().deleteUser(conv(item));
    }

}
