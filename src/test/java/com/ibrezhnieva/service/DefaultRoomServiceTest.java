package com.ibrezhnieva.service;

import com.ibrezhneva.domain.Room;
import com.ibrezhneva.service.RoomService;
import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:app.properties")
@ContextConfiguration(initializers = DefaultRoomServiceTest.Initializer.class,
        locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class DefaultRoomServiceTest {

    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>();

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
                    "jdbc.url=" + postgreSQLContainer.getJdbcUrl(),
                    "jdbc.username=" + postgreSQLContainer.getUsername(),
                    "jdbc.password=" + postgreSQLContainer.getPassword());
        }
    }

    @Autowired
    private RoomService roomService;

    @Test
    public void testGetRoomById() {
        Room roomById = roomService.getRoomById(1);
        assertEquals("room1", roomById.getName());
    }

    @Test
    public void testCreateRoom() {
        roomService.createRoom(3, "room3");
        Room roomById = roomService.getRoomById(3);
        assertEquals("room3", roomById.getName());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testCreateRoom_AlreadyExist() {
        roomService.createRoom(1, "my_test_room");
    }

    @Test
    public void testCreateRoomWithException() {
        try {
            roomService.createRoomWithException(4, "room4");
        } catch (RuntimeException e) {
            //expected
        }
        Room roomById = roomService.getRoomById(4);
        assertEquals("room4", roomById.getName()); //!!!
    }

}