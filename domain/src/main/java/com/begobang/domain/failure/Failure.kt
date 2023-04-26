package com.begobang.domain.failure

/*
    Failure is the class that will be returned in case the use case and service fails.
    I created a BaseFailure, but we can create more types of Failures extending Failure. In this way
    in the requestApi function we can use any type of Failure.
 */
sealed class Failure(val exception: String? = null) {
    class BaseFailure(val code: String? = "0", message: String? = null): Failure(message)
}
