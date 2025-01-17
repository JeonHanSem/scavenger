package com.navercorp.scavenger.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OperationServiceTest {

    @Autowired
    lateinit var sut: OperationService

    @Test
    fun dispatch() {
        sut.dispatch()
        sut.dispatch()
    }
}
