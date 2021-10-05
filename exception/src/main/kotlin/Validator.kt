import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class NameValidator : Validator<String>() {
    val NAME_VALIDATOR_PATTERN = Pattern.compile("[аА-яЯ]+")

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) {
            return listOf(ErrorCode.VALUE_IS_NULL)
        }

        if (!value.matches(NAME_VALIDATOR_PATTERN.toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER_IN_NAME)
        }

        if (value.length > 16) {
            return listOf(ErrorCode.SIZE_OF_NAME_IS_TOO_LONG)
        }
        return listOf()
    }
}

class PhoneValidator : Validator<String>() {
    val PHONE_NUMBER_PATTERN = Pattern.compile("(7|8)\\d+")

    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.VALUE_IS_NULL)
        }

        if (!value.matches(PHONE_NUMBER_PATTERN.toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER_IN_PHONE)
        }
        if (value.length != 11) {
            return listOf(ErrorCode.INCORRECT_SIZE_OF_NUMBER)
        }
        return listOf()
    }
}

class EmailValidator : Validator<String>() {
    val EMAIL_VALIDATOR_PATTERN = Pattern.compile("[aA-zZ]+@[a-z]+.[a-z]+")

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) {
            return listOf(ErrorCode.VALUE_IS_NULL)
        }

        if (!value.matches(EMAIL_VALIDATOR_PATTERN.toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER_IN_EMAIL)
        }

        if (value.length > 32) {
            return listOf(ErrorCode.SIZE_OF_EMAIL_IS_TOO_LONG)
        }
        return listOf()
    }
}

class SnilsValidator : Validator<String>() {
    val SNILS_VALIDATOR_PATTERN = Pattern.compile("\\d+")

    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.VALUE_IS_NULL)
        }

        if (!value.matches(SNILS_VALIDATOR_PATTERN.toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER_IN_SNILS)
        }

        if (value.length != 11) {
            return listOf(ErrorCode.INCORRECT_SIZE_OF_SNILS)
        }

        if (calculateControlSum(value) % 101 != value.substring(9, 11).toInt()) {
            return listOf(ErrorCode.INVALID_SNILS_CONTROL_SUM)
        }
        return listOf()
    }

    fun calculateControlSum(snils: String): Int {
        var controlSum = 0
        var i = 9
        snils.substring(0, 9).forEach { numberInSnils ->
            controlSum += Character.getNumericValue(numberInSnils) * i
            i--
        }
        return controlSum
    }
}




