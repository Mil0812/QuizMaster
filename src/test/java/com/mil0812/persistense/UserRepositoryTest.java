package com.mil0812.persistense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.PropertyManager;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.entity.impl.User.Status;
import com.mil0812.persistence.exception.ErrorOnUpdate;
import com.mil0812.persistence.repository.impl.UserRepositoryImpl;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.repository.mappers.impl.UserRowMapper;
import com.mil0812.persistense.init.FakeDatabaseInitializer;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class UserRepositoryTest {

  private static ConnectionManager connectionManager;
  private static UserRepository userRepository;

  @BeforeAll
  static void setup() {
    PropertyManager propertyManager = new PropertyManager(
        UserRepositoryTest.class.getClassLoader()
            .getResourceAsStream("application.properties")
    );
    connectionManager = new ConnectionManager(propertyManager);
    userRepository = new UserRepositoryImpl(connectionManager, new UserRowMapper());
  }

  @BeforeEach
  void init() throws SQLException {
    FakeDatabaseInitializer.run(connectionManager.get());
  }

  @Test
  void findById_whenUserExists_thenReturnsUser() {
    // Given
    UUID userId = UUID.fromString("ce66389c-ab6e-42d8-aa79-b6b9abf41cdb");
    User expectedUser = new User(userId,
        "lollipop321",
        "lily098",
        "Фанні",
        "Марвен",
        "lollipop@i.ua",
        Status.STUDENT);

    // When
    Optional<User> actualOptionalUser = userRepository.findById(userId);

    // Then
    assertTrue(actualOptionalUser.isPresent(), "Шуканий користувач існує!");
    assertEquals(expectedUser, actualOptionalUser.get(), "Знайдений користувач = шуканому!");
  }

  @Test
  void findById_whenUserDoesNotExist_thenReturnsEmptyOptional() {
    // Given
    UUID id = UUID.randomUUID();

    // When
    Optional<User> actualOptionalUser = userRepository.findById(id);

    // Then
    assertTrue(actualOptionalUser.isEmpty(), "Порожнє значення: користувач за таким id не існує");
  }

  @Test
  void findBy_whenUserExists_thenReturnsUser() {
    // Given
    User expectedUser = new User(
        UUID.fromString("93966090-3acc-41c2-a88c-1808fbcb60a1"),
        "millennium",
        "Mmm7",
        "Міа",
        "Лейм",
        "mia-leim1@gmail.com",
        Status.STUDENT
    );

    // When
    Optional<User> actualOptionalUser = userRepository.findBy("lastName", "Лейм");

    // Then
    assertTrue(actualOptionalUser.isPresent(), "Знайдений об'єкт Користувач існує!");
    assertEquals(expectedUser, actualOptionalUser.get(), "Шуканий об'єкт = знайденому!");
  }

  @Test
  void findBy_whenUserDoesNotExist_thenReturnsEmptyOptional() {
    // Given
    User expectedUser = new User(
        UUID.fromString("93966090-3acc-41c2-a88c-1808fbcb60a1"),
        "millenium",
        "Mmm7",
        "Міа",
        "Лейм",
        "mia-leim1@gmail.com",
        Status.STUDENT
    );

    // When
    Optional<User> actualOptionalUser = userRepository.findBy("lastName", "Фаст");

    // Then
    assertTrue(actualOptionalUser.isEmpty(),
        "Порожнє значення: користувача з таким прізвищем не існує");
  }

  @Test
  void findByLogin_whenUserExists_thenReturnsUser() {
    // Given
    User expectedUser = new User(
        UUID.fromString("3b5a1d93-6a0b-42c1-b1c8-25caedec9fe9"),
        "waX99",
        "Wax00",
        "Мелорі",
        "Джонсон",
        "wax@i.ua",
        Status.TEACHER
    );

    // When
    Optional<User> actualOptionalUser = userRepository.findByLogin("waX99");

    // Then
    assertTrue(actualOptionalUser.isPresent(), "Шуканий об'єкт Користувач існує!");
    assertEquals(expectedUser, actualOptionalUser.get(), "Шуканий об'єкт = знайденому!");
  }

  @Test
  void findByLogin_whenUserDoesNotExist_thenReturnsEmptyOptional() {
    // Given
    User expectedUser = new User(
        UUID.fromString("3b5a1d93-6a0b-42c1-b1c8-25caedec9fe9"),
        "waX99",
        "Wax00",
        "Мелорі",
        "Джонсон",
        "wax@i.ua",
        Status.TEACHER
    );

    // When
    Optional<User> actualOptionalUser = userRepository.findByLogin("melory");

    // Then
    assertTrue(actualOptionalUser.isEmpty(),
        "Порожнє значення: користувача з таким логіном не існує");
  }

  @Test
  void findByEmail_whenUserExists_thenReturnsUser() {
    // Given
    User expectedUser = new User(
        UUID.fromString("e969b133-c007-419b-bd64-3fbba78ecb2d"),
        "sunny",
        "777",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER);

    // When
    Optional<User> actualOptionalUser = userRepository.findByEmail("suunnyyy@gmail.com");

    // Then
    assertTrue(actualOptionalUser.isPresent(), "Шуканий об'єкт Користувач існує!");
    assertEquals(expectedUser, actualOptionalUser.get(), "Шуканий об'єкт = знайденому!");
  }

  @Test
  void findByEmail_whenDoesNotExist_thenEmptyOptional() {
    // Given
    User expectedUser = new User(
        UUID.fromString("e969b133-c007-419b-bd64-3fbba78ecb2d"),
        "sunny",
        "777",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER);

    // When
    Optional<User> actualOptionalUser = userRepository.findByEmail("sunny@i.ua");

    // Then
    assertTrue(actualOptionalUser.isEmpty(),
        "Порожнє значення: користувача з такою електронною адресою не існує");
  }

  @Test
  @Tag("slow")
  void findAll_thenReturnsSetOfUser() {
    // Given
    int usersSize = 4;

    // When
    Set<User> users = userRepository.findAll();

    // Then
    assertNotNull(users);
    assertEquals(usersSize, users.size());
  }

  @Test
  @Tag("slow")
  void count_thenReturnsCountOfRows() {
    // Given
    int usersSize = 4;

    // When
    long count = userRepository.count();

    // Then
    assertNotEquals(count, 0);
    assertEquals(usersSize, count);
  }

  @Test
  void save_whenInsertNewUser_thenReturnsUserWithGeneratedId() {
    // Given
    User expectedUser = new User(
        null,
        "mike89",
        "password000",
        "Майк",
        "Лейбренд",
        "mike_mike@gmail.com",
        Status.STUDENT
    );

    // When
    User actualUser = userRepository.save(expectedUser);
    UUID id = actualUser.id();
    Optional<User> optionalFoundedUser = userRepository.findById(id);

    // Then
    assertNotNull(id);
    assertTrue(optionalFoundedUser.isPresent());
    assertEquals(actualUser, optionalFoundedUser.orElse(null));
  }

  @Test
  void save_whenInsertCollectionOfUsers_thenReturnsCollectionOfUsersWithGeneratedId() {

    // Given
    Set<User> users = new LinkedHashSet<>(3);
    users.add(new User(
        null,
        "sandora",
        "just_smile",
        "Сандра",
        "Лебідь",
        "sandoraa@gmail.com",
        Status.TEACHER
    ));
    users.add(new User(
        null,
        "taras321",
        "qwerty098",
        "Тарас",
        "Левенець",
        "taras_levenetz@i.ua",
        Status.TEACHER
    ));
    users.add(new User(
        null,
        "yarema",
        "love_ukraine",
        "Ярема",
        "Голуб",
        "yarema_uk@gmail.com",
        Status.STUDENT
    ));

    // When
    Set<User> expectedUsers = userRepository.save(users);
    List<String> ids = expectedUsers.stream().map(User::id).map(id -> STR."'\{id}'").toList();
    Set<User> actualUsers = userRepository.findAllWhere(STR."id IN(\{String.join(", ", ids)})");

    // Then
    assertNotNull(ids);
    assertEquals(users.size(), expectedUsers.size());
    assertEquals(expectedUsers, actualUsers);
  }

  @Test
  void save_whenUpdateExistUser_thenReturnsUser() {
    // Given
    UUID userId = UUID.fromString("e969b133-c007-419b-bd64-3fbba78ecb2d");
    User expectedUser = new User(
        userId,
        "sunny",
        "sunny777",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER);
    // password is changed

    // When
    userRepository.save(expectedUser);
    var optionalUser = userRepository.findById(userId);

    // Then
    assertEquals(expectedUser, optionalUser.orElse(null));
  }

  @Test
  void save_whenUpdateNotExistUser_thenThrowEntityUpdateException() {
    // Given
    UUID userId = UUID.randomUUID();
    User expectedUser = new User(
        userId,
        "sunny",
        "sunny777",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER);

    // When
    Executable executable = () -> {
      userRepository.save(expectedUser);
      var optionalUser = userRepository.findById(userId);
    };

    // Then
    assertThrows(ErrorOnUpdate.class, executable);
  }

  @Test
  void save_whenUpdateCollectionOfUsers_thenReturnsCollectionOfUsers() {
    // Given
    Set<User> users = new LinkedHashSet<>(3);
    users.add(new User(
        UUID.fromString("93966090-3acc-41c2-a88c-1808fbcb60a1"),
        "millenium",
        "Mmm897",
        "Міа",
        "Лейм",
        "mia-leim1@gmail.com",
        Status.STUDENT
    ));
    users.add(new User(
        UUID.fromString("3b5a1d93-6a0b-42c1-b1c8-25caedec9fe9"),
        "waX99",
        "Wax800",
        "Мелорі",
        "Джонсон",
        "wax@i.ua",
        Status.TEACHER
    ));
    users.add(new User(
        UUID.fromString("e969b133-c007-419b-bd64-3fbba78ecb2d"),
        "sunny",
        "7s7s7",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER
    ));
    long expectedCount = userRepository.count();

    // When
    Set<User> expectedUsers = userRepository.save(users);
    List<String> ids = expectedUsers.stream().map(User::id).map(id -> STR."'\{id}'").toList();
    Set<User> actualUsers = userRepository.findAllWhere(STR."id IN(\{String.join(", ", ids)})");
    long actualCount = userRepository.count();

    // Then
    assertNotNull(expectedUsers);
    assertEquals(users.size(), expectedUsers.size());
    assertEquals(expectedCount, actualCount);
    assertEquals(expectedUsers, actualUsers);
  }

  @Test
  void save_whenUpdateNotExistCollectionOfUsers_thenThrowEntityUpdateException() {
    // Given
    UUID userId = UUID.randomUUID();
    Set<User> users = new LinkedHashSet<>(3);
    users.add(new User(
        userId,
        "sunny",
        "7s7s7s7",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER
    ));
    users.add(new User(
        UUID.fromString("3b5a1d93-6a0b-42c1-b1c8-25caedec9fe9"),
        "waX919",
        "Wax800",
        "Мелорі",
        "Джонсон",
        "wax@i.ua",
        Status.TEACHER
    ));
    users.add(new User(
        UUID.fromString("e969b133-c007-419b-bd64-3fbba78ecb2d"),
        "sunny",
        "7s7s7s7",
        "Аделія",
        "Хоуп",
        "suunnyyy@gmail.com",
        Status.TEACHER
    ));

    // When
    Executable executable = () -> {
      userRepository.save(users);
      var optionalUser = userRepository.findById(userId);
    };

    // Then
    assertThrows(ErrorOnUpdate.class, executable);
  }

  @Test
  void delete_whenUserExists_ThenReturnsTrue() {
    // Given
    UUID id = UUID.fromString("ce66389c-ab6e-42d8-aa79-b6b9abf41cdb");
    boolean expected = true;

    // When
    boolean actual = userRepository.delete(id);

    // Then
    assertEquals(expected, actual, "Користувача успішно видалено!");
  }

  @Test
  void delete_whenUserDoesNotExist_thenReturnsFalse() {
    // Given
    UUID id = UUID.randomUUID();
    boolean expected = false;

    // When
    boolean actual = userRepository.delete(id);

    // Then
    assertEquals(expected, actual, "Користувача з таким id не існує");
  }

  @AfterAll
  static void tearDown() throws SQLException {
    connectionManager.closePool();
  }

}
