package org.example.github.service;

import org.example.github.model.domain.Repository;
import reactor.core.publisher.Flux;

public interface GitHubRepositoryService {
    public Flux<Repository> getRepositories(String userName);
}
