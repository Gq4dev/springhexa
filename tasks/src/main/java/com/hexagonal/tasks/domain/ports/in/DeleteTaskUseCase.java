package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.models.Task;

import java.util.Optional;

public interface DeleteTaskUseCase {
    boolean deleteTask(Long id);
}
