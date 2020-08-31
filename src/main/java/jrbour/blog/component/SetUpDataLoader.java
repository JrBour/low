package jrbour.blog.component;

import jrbour.blog.dao.RoleDao;
import jrbour.blog.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

// A component is a class that can be autodetect by Spring for dependency injection
// For spring, a Service, Repository and a Controller are Component too
// We use ApplicationListener<ContextRefreshedEvent> for call the class SetUpDataLoader at the beginning when we run the app
@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    // Flag for launch or not the fixtures
    boolean alreadySetup = false;

    // Autowiring is used for use dependency injection directly from  property instead of use a constructor
    @Autowired
    private RoleDao roledao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup)
            return;
        createRoleIfNotExist("ROLE_ADMIN");
        createRoleIfNotExist("ROLE_USER");
    }

    // A transaction is a bunch of instruction that must be run in one block. If an instruction doesn't works, the transaction is cancel and rollback.
    // If one instruction fail, all the instruction doesn't take effect
    @Transactional
    void createRoleIfNotExist(String name) {
        Role role = this.roledao.findByName(name);
        if(role == null){
            role = new Role();
            role.setName(name);
            this.roledao.save(role);
        }
    }
}
