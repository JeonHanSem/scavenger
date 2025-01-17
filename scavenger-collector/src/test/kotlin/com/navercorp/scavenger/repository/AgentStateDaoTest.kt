package com.navercorp.scavenger.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AgentStateDaoTest {
    @Autowired
    private lateinit var sut: AgentStateDao

    @Test
    @Transactional
    fun deleteGarbageLastPolledAtBefore() {
        val now = Instant.now()
        sut.findAll().map { it.customerId }.forEach {
            sut.deleteGarbageLastPolledAtBefore(it, now)
        }
        assertThat(sut.findAll().filter { it.lastPolledAt <= now }).isEmpty()
    }

    @Test
    @Transactional
    fun updateTimestampsAndEnabled() {
        val now = Instant.now()

        sut.updateTimestampsAndEnabled(2, "d0dfa3c2-809c-428f-b501-7419197d91c5", now, now, true)

        assertThat(sut.findAllByCustomerId(2)).isNotEmpty
    }
}
