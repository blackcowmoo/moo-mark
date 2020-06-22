// package com.blackcowmoo.moomark.repository;

// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.List;

// import com.blackcowmoo.moomark.model.User;

// import org.junit.After;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class UserRepositoryTest {

// @Autowired
// UserRepository userRepository;

// @After
// public void cleanup() {
// userRepository.deleteAll();
// }

// @Test
// public void 유저레포지토리테스트() {
// // given
// int userType = 0;
// String userName = "tester";

// userRepository.save(User.builder().userType(userType).userName(userName).build());

// // when
// List<User> userList = userRepository.findAll();

// // then
// User user = userList.get(0);
// assertThat(user.getUserType()).isEqualTo(userType);
// assertThat(user.getUserName()).isEqualTo(userName);
// }
// }