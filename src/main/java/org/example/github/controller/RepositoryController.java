package org.example.github.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.github.model.domain.Repository;
import org.example.github.service.GitHubRepositoryService;
import org.example.github.service.GithubRepositoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "Repository API", description = "APIs to get repository resources")
@RestController
@RequestMapping("api/v1/repository")
public class RepositoryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryController.class);

    private final GitHubRepositoryService githubRepositoryService;

    @Autowired
    public RepositoryController(final GitHubRepositoryService repositoryService) {
        this.githubRepositoryService = repositoryService;
    }

    @Operation(summary = "List the repositories owned by the user")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping(path = "/{userName}")
    public Flux<Repository> getRepositoryInformation(@Parameter(name = "Accept", hidden = true)
                                                     @RequestHeader(name = "Accept", value = "Accept", required = false, defaultValue = "application/json") String responseContentType,
                                                     @Parameter(name = "userName", description = "user name to fetch repositories")
                                                     @PathVariable("userName") String userName) {
        logger.info("Beginning of getRepositoryInformation.");
        throwIfContentTypeNotSupported(responseContentType);
        return githubRepositoryService.getRepositories(userName);
    }

}
