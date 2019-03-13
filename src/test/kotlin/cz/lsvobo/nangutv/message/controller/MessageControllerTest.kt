package cz.lsvobo.nangutv.message.controller

import com.fasterxml.jackson.databind.ObjectMapper
import cz.lsvobo.nangutv.message.repository.Message
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    @Throws(Exception::class)
    fun `Should return empty list when no message exists`() {
        mockMvc.perform(get("/api/v1/messages"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("[]"))
    }

    @Test
    @Throws(Exception::class)
    fun `Should create a new message when payload is OK`() {
        val message = Message()
        message.message = "message1"
        message.author = "author1"

        val mvcResult = mockMvc.perform(post("/api/v1/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(message)))
                .andDo(print())
                .andExpect(status().isCreated)
                .andReturn()

        val savedMessage = mapper
                .readValue(mvcResult.response.contentAsByteArray, Message::class.java)

        assertThat(savedMessage.id, `is`(notNullValue()))
        assertThat(savedMessage.author, `is`(message.author))
        assertThat(savedMessage.message, `is`(message.message))
    }

    // TODO more tests not implemented due to limited time
}
