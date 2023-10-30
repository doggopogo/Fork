package ca.etsmtl.log.fitnesshabits.toDelete.api.model

data class OperationResult(
    val isSuccess : Boolean = false,
    val statusCode: Int = HttpStatusCode.STATUS_CODE_SERVER_ERROR,
    val message: String = "The cellphone was unable to send the request",
    val data: Any? = null
)