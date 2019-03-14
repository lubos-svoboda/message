package cz.lsvobo.nangutv.message.controller

import cz.lsvobo.nangutv.message.repository.Message
import cz.lsvobo.nangutv.message.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/api/v1/messages"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class MessageController {

    @Autowired
    lateinit var messageRepository: MessageRepository

    @GetMapping
    fun findAll(): Iterable<Message> {
        return messageRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Message> {
        val message = messageRepository.findById(id)
        return if (message.isPresent) {
            ResponseEntity.ok(message.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/search")
    fun findByUsername(@RequestParam author: String): Iterable<Message> {
        return messageRepository.findByAuthor(author)
    }

    @PostMapping
    fun save(@RequestBody message: Message): ResponseEntity<Message> {
        val savedMessage = messageRepository.save(message)
        val location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMessage.id).toUri()
        return ResponseEntity.created(location).body(savedMessage)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody message: Message): ResponseEntity<Void> {
        messageRepository.save(message)
        val messageOptional = messageRepository.findById(id)
        return if (messageOptional.isPresent) {
            message.id = id
            messageRepository.save(message)
            ResponseEntity.noContent().build()
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        val message = messageRepository.findById(id)
        return if (message.isPresent) {
            messageRepository.delete(message.get())
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
