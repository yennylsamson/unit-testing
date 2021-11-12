package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();;
    }

    @Test
    void itShouldCheckWhenStudentSelectEmailExists() {
        //given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                "jamila@gmail.com",
                Gender.FEMALE
        );
        underTest.save(student);
        //when
        boolean expected = underTest.selectExistsEmail("jamila@gmail.com");
        //then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckWhenStudentSelectEmailDoesNotExists() {
        //given
        String email = "jamila@gmail.com";
        //when
        boolean expected = underTest.selectExistsEmail("jamila@gmail.com");

        //then
        assertThat(expected).isFalse();

    }
}