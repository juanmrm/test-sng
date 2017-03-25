package com.sng.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sng.dto.Message;

/**
 * Creamos un repositorio para mongo.
 * @author juanmiguel
 *
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String>{

}
