package com.navercorp.scavenger.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerDaoTest {

    @Autowired
    private lateinit var sut: CustomerDao

    @Test
    fun findAll() {
        assertThat(sut.findAll()).isNotEmpty
    }

    @Test
    fun findFirstByLicenseKey() {
        assertThat(sut.findByLicenseKey("4c94e0dd-ad04-4b17-9238-f46bba75c684")).isNotNull
    }
}
