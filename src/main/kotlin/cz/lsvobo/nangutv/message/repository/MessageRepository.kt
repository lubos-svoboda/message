package cz.lsvobo.nangutv.message.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<Message, Long> {

    fun findByAuthor(author: String): List<Message>
}
