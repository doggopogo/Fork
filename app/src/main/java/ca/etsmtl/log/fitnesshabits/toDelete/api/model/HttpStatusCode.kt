package ca.etsmtl.log.fitnesshabits.toDelete.api.model

object HttpStatusCode {
    /** Status code for a successful request.  */
    const val STATUS_CODE_OK = 200

    /* Status code for a successful request with no content information.    */
    const val STATUS_CODE_NO_CONTENT = 204

    /** Status code for a resource corresponding to any one of a set of representations.  */
    const val STATUS_CODE_MULTIPLE_CHOICES = 300

    /** Status code for a resource that has permanently moved to a new URI.  */
    const val STATUS_CODE_MOVED_PERMANENTLY = 301

    /** Status code for a resource that has temporarily moved to a new URI.  */
    const val STATUS_CODE_FOUND = 302

    /** Status code for a resource that has moved to a new URI and should be retrieved using GET.  */
    const val STATUS_CODE_SEE_OTHER = 303

    /** Status code for a resource that access is allowed but the document has not been modified.  */
    const val STATUS_CODE_NOT_MODIFIED = 304

    /** Status code for a resource that has temporarily moved to a new URI.  */
    const val STATUS_CODE_TEMPORARY_REDIRECT = 307

    /** Status code for a request that requires user authentication.  */
    const val STATUS_CODE_UNAUTHORIZED = 401

    /** Status code for a server that understood the request, but is refusing to fulfill it.  */
    const val STATUS_CODE_FORBIDDEN = 403

    /** Status code for a server that has not found anything matching the Request-URI.  */
    const val STATUS_CODE_NOT_FOUND = 404

    const val STATUS_CODE_REQUEST_TIMEOUT = 408

    /** Status code for an internal server error.  */
    const val STATUS_CODE_SERVER_ERROR = 500

    /** Status code for a bad gateway.  */
    const val STATUS_CODE_BAD_GATEWAY = 502

    /** Status code for a service that is unavailable on the server.  */
    const val STATUS_CODE_SERVICE_UNAVAILABLE = 503

    /**
     * Returns whether the given HTTP response status code is a success code `>= 200 and < 300`.
     */
    fun isSuccess(statusCode: Int): Boolean {
        return statusCode in STATUS_CODE_OK until STATUS_CODE_MULTIPLE_CHOICES
    }

    /**
     * Returns whether the given HTTP response status code is a redirect code
     * `301, 302, 303, 307`.
     */
    fun isRedirect(statusCode: Int): Boolean {
        return when (statusCode) {
            STATUS_CODE_MOVED_PERMANENTLY, STATUS_CODE_FOUND, STATUS_CODE_SEE_OTHER, STATUS_CODE_TEMPORARY_REDIRECT -> true
            else -> false
        }
    }
}