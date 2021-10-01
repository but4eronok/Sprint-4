class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER_IN_NAME(100, "Недопустимые символы в имени/фамилии"),
    INVALID_CHARACTER_IN_PHONE(101, "Недопустимые символы в телефонном номере"),
    INVALID_CHARACTER_IN_EMAIL(102, "Недопустимые символы в почте"),
    INVALID_CHARACTER_IN_SNILS(103, "Недопустимые символы в снилс"),
    VALUE_IS_NULL(104, "Пустое поле"),
    INCORRECT_SIZE_OF_NUMBER(105, "Неверный формат номера"),
    SIZE_OF_NAME_IS_TOO_LONG(106, "Слишком много символов в строке имени/фамилии"),
    SIZE_OF_EMAIL_IS_TOO_LONG(107, "Слишком много символов в строке почта"),
    INCORRECT_SIZE_OF_SNILS(108, "Слишком много символов в строке снилс"),
    INVALID_SNILS_CONTROL_SUM(109, "Невалидное контрольное число в снилс")
}