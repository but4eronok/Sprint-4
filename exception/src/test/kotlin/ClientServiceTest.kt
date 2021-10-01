import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[1], ErrorCode.SIZE_OF_NAME_IS_TOO_LONG)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_CHARACTER_IN_PHONE)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_SNILS_CONTROL_SUM)
    }

    @Test
    fun `fail save client - name on english`() {
        val client = getClientFromJson("/fail/user_invalid_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER_IN_NAME)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_CHARACTER_IN_NAME)
    }

    @Test
    fun `fail save client - incorrect size of number`() {
        val client = getClientFromJson("/fail/user_incorrect_number.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_SIZE_OF_NUMBER)
    }

    @Test
    fun `fail save client - too long email`() {
        val client = getClientFromJson("/fail/user_long_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.SIZE_OF_EMAIL_IS_TOO_LONG)
    }

    @Test
    fun `fail save client - incorrect size of snils`() {
        val client = getClientFromJson("/fail/user_snils_issue.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_SIZE_OF_SNILS)
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}