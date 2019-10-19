package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.businessbean.User;

public interface UserMAO extends GenericMAO<User, Long>{

    public User getByEmail(String email);

}
