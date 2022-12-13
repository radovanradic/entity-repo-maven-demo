package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.Optional;

@MicronautTest
class EntityRepoMavenDemoTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    MyRepo myRepo;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testRepo() {
        MyEntity myEntity = new MyEntity();
        myEntity.setName("Test");
        myEntity = myRepo.save(myEntity);
        Long id = myEntity.getId();

        Optional<MyEntity> optEntity = myRepo.findById(id);
        Assertions.assertTrue(optEntity.isPresent());
        Assertions.assertEquals("Test", optEntity.get().getName());

        myEntity.setName("Test2");
        myRepo.updateAll(Arrays.asList(myEntity));

        optEntity = myRepo.findById(id);
        Assertions.assertEquals("Test2", optEntity.get().getName());
    }
}
