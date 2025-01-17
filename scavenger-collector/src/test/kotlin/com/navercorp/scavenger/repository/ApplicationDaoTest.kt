package com.navercorp.scavenger.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationDaoTest {
    @Autowired
    private lateinit var sut: ApplicationDao

    @Test
    @Transactional
    fun upsert() {
        assertThat(sut.upsert(1, "vitessTest", Instant.now())).isGreaterThanOrEqualTo(0L)
    }
}
