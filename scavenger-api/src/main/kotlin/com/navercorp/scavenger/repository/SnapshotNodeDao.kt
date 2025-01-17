package com.navercorp.scavenger.repository

import com.navercorp.scavenger.entity.SnapshotNode
import com.navercorp.scavenger.repository.sql.SnapshotNodeSql
import com.navercorp.spring.data.jdbc.plus.sql.provider.EntityJdbcProvider
import com.navercorp.spring.data.jdbc.plus.sql.support.JdbcDaoSupport
import org.springframework.stereotype.Repository

@Repository
class SnapshotNodeDao(
    entityJdbcProvider: EntityJdbcProvider,
    snapshotNodeRepository: SnapshotNodeRepository
) : JdbcDaoSupport(entityJdbcProvider), SnapshotNodeRepository by snapshotNodeRepository {
    private val sql: SnapshotNodeSql = super.sqls(::SnapshotNodeSql)

    fun saveAllSnapshotNodes(entities: List<SnapshotNode>) {
        jdbcOperations.batchUpdate(
            sql.insert(),
            entities.map { beanParameterSource(it) }.toTypedArray()
        )
    }

    fun selectAllBySignatureContaining(
        customerId: Long,
        snapshotId: Long,
        signature: String,
        id: Long? = null
    ): List<SnapshotNode> {
        return select(
            sql.selectAllBySignatureContaining(id),
            mapParameterSource()
                .addValue("customerId", customerId)
                .addValue("snapshotId", snapshotId)
                .addValue("signature", signature)
                .addValue("id", id),
            SnapshotNode::class.java
        )
    }
}
