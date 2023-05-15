package com.example.repositories;

import com.example.entities.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat,Long> {
}
