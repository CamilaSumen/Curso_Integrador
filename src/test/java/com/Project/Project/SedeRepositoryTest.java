package com.Project.Project;

import com.Project.Project.Modelo.Sede;
import com.Project.Project.Modelo.Repository.SedeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SedeRepositoryTest {

    @Autowired
    private SedeRepository repositorio;

    @Test
    public void testCrearSede(){
        Sede sedeGuardada = repositorio.save(new Sede("Sede Ica"));
        assertThat(sedeGuardada.getId()).isGreaterThan(0);
    }

}
