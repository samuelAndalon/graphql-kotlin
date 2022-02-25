package com.expediagroup.graphql.transactionbatcher.instrumentation.level

import com.expediagroup.graphql.transactionbatcher.instrumentation.level.execution.AbstractExecutionLevelInstrumentation
import com.expediagroup.graphql.transactionbatcher.instrumentation.level.execution.ExecutionLevelInstrumentationContext
import com.expediagroup.graphql.transactionbatcher.instrumentation.level.execution.ExecutionLevelInstrumentationParameters
import com.expediagroup.graphql.transactionbatcher.instrumentation.level.state.Level
import com.expediagroup.graphql.transactionbatcher.transaction.TransactionBatcher

class TransactionBatcherLevelInstrumentation : AbstractExecutionLevelInstrumentation() {
    override fun beginExecutionLevel(
        parameters: ExecutionLevelInstrumentationParameters
    ): ExecutionLevelInstrumentationContext =
        object : ExecutionLevelInstrumentationContext {
            override fun onLevelDispatched(level: Level) {
                parameters
                    .executionContext
                    .graphQLContext.get<TransactionBatcher>(TransactionBatcher::class)
                    ?.dispatch()
            }
        }
}
