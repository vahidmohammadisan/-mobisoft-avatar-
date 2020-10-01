package ir.vahidmohammadisan.mobisoft.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class ForceChangePassException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class ValidationExceptoin(message: String) : IOException(message)