package com.expediagroup.graphql.transactionbatcher.instrumentation

import com.expediagroup.graphql.transactionbatcher.instrumentation.execution.AbstractExecutionLevelInstrumentation
import com.expediagroup.graphql.transactionbatcher.instrumentation.execution.ExecutionLevelInstrumentationContext
import com.expediagroup.graphql.transactionbatcher.instrumentation.execution.ExecutionLevelInstrumentationParameters
import com.expediagroup.graphql.transactionbatcher.transaction.TransactionBatcher

class TransactionBatcherLevelInstrumentation : AbstractExecutionLevelInstrumentation() {
    override fun beginExecutionLevel(
        parameters: ExecutionLevelInstrumentationParameters
    ): ExecutionLevelInstrumentationContext = ExecutionLevelInstrumentationContext {
        parameters
            .executionContext
            .graphQLContext.get<TransactionBatcher>(TransactionBatcher::class)
            ?.dispatch()
    }
}
