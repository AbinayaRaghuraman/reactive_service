package org.example.github;

import org.example.github.controller.RepositoryController;
import org.example.github.model.domain.Branch;
import org.example.github.model.domain.Repository;
import org.example.github.service.GithubRepositoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers =RepositoryController.class)
@Import(GithubRepositoryServiceImpl.class)
public class ApplicationTest {
    @Autowired
    private WebTestClient webClientMock;

    @MockBean
    private GithubRepositoryServiceImpl githubRepositoryService;

    @Test
    public void test_getRepositories_Success()
    {
        List<Branch> branchList = new ArrayList<>();
        Branch branch = new Branch("main","kahdjsada77d8a68");
        branchList.add(branch);


        Flux<Repository> repositoryFlux = Flux.just(new Repository("reactive", "Abi", branchList));
        when(githubRepositoryService.getRepositories("AbinayaRaghuraman")).thenReturn(repositoryFlux);

        webClientMock.get().uri("/api/v1/repository/{username}","AbinayaRaghuraman")
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(repositoryFlux)
                .expectSubscription()
                .expectNextMatches(p->p.getName().equals("reactive"))
                .verifyComplete();
    }
    @Test
    public void test_SuccessMethodCall()
    {
        List<Branch> branchList = new ArrayList<>();
        Branch branch = new Branch("main","kahdjsada77d8a68");
        branchList.add(branch);
        Repository repo = new Repository("reactive", "Abi", branchList);

        Flux<Repository> repositoryFlux = Flux.just(repo);

        Mockito.
        when(githubRepositoryService.getRepositories("AbinayaRaghuraman")).thenReturn(repositoryFlux);

        webClientMock.get().uri("/api/v1/repository/{username}","AbinayaRaghuraman")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Repository.class);

        Mockito.verify(githubRepositoryService, times(1)).getRepositories("AbinayaRaghuraman");
    }

    @Test
    public void test_IncorrectContentType()
    {
        webClientMock.get().uri("/api/v1/repository/{username}","AbinayaRaghuraman")
                .header(HttpHeaders.ACCEPT, "application/xml")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    public void test_getRepositories_notFound()
    {
        when(githubRepositoryService.getRepositories("unknown")).thenThrow(new WebClientResponseException(404,"Not found",null,null,null));

        webClientMock.get().uri("/api/v1/repository/{username}","unknown")
                .exchange()
                .expectStatus().isNotFound();
    }

}
