package org.project2.tz1_cinema.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // Указывает использование встроенной базы данных
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void UserRepository_saveUser(){
        Users user = new Users();
        user.setName("test");
        user.setLast_name("test_last_name");
        user.setEmail("test@test.com");
        user.setPassword("test_password");
        user.setRole(Role.USER);
        Users savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
    }
    @Test
    public void UserRepository_updateUser(){
        Users user = new Users();
        user.setName("test");
        user.setLast_name("test_last_name");
        user.setEmail("test@test.com");
        user.setPassword("test_password");
        user.setRole(Role.USER);
        Users savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
    }
    @Test
    void UserRepository_deleteUser() {
        // Создать пользователя
        Users user = new Users();
        user.setName("John");
        user.setLast_name("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setRole(Role.USER);

        // Сохранить пользователя
        userRepository.save(user);

        // Удалить пользователя
        userRepository.delete(user);

        // Проверить, что пользователь удалён
        Optional<Users> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isEmpty(), "User should not exist in the database after deletion");
    }
    @Test void UserRepository_findUserByEmail(){
        Users user = new Users();
        user.setName("test");
        user.setLast_name("test_last_name");
        user.setEmail("test@test.com");
        user.setPassword("test_password");
        user.setRole(Role.USER);
        userRepository.save(user);
        Users savedUser = userRepository.findUsersByEmail(user.getEmail());
        Assertions.assertNotNull(savedUser);
    }
    @Test void UserRepository_findUsersByName(){
        Users user = new Users();
        user.setName("test");
        user.setLast_name("test_last_name");
        user.setEmail("test@test.com");
        user.setPassword("test_password");
        user.setRole(Role.USER);
        userRepository.save(user);
        Users savedUser = userRepository.findUsersByName(user.getName());
        Assertions.assertNotNull(savedUser);
    }
}

