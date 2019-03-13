package cz.lsvobo.nangutv.message.repository

import javax.persistence.*

@Entity
@Table(name = "MESSAGE", indexes = [Index(name = "IDX_MESSAGE_AUTHOR", columnList = "AUTHOR")])
class Message {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "MESSAGE", nullable = false)
    var message: String = ""

    @Column(name = "AUTHOR", nullable = false)
    var author: String = ""
}
