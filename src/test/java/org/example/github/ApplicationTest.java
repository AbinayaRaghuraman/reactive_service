package org.example.github;

import org.example.github.controller.RepositoryController;
import org.example.github.model.domain.Branch;
import org.example.github.model.domain.Repository;
import org.example.github.service.GithubRepositoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(RepositoryController.class)
public class ApplicationTest {
    @Autowired
    private WebTestClient webClientMock;

    @MockBean
    private GithubRepositoryServiceImpl githubRepositoryService;

    @Test
    public void test_getRepositories()
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
    public void test_getRepositories_notFound()
    {
        //List<Branch> branchList = new ArrayList<>();
        //Branch branch = new Branch("main","kahdjsada77d8a68");
//        //branchList.add(branch);
//
//        //Flux<Repository> repositoryFlux = Flux.just(new Repository("","",new ArrayList<>()));
//        when(githubRepositoryService.getRepositories("unknown")).thenThrow(new WebClientException(404));
//
//        webClientMock.get().uri("/api/v1/repository/{username}","unknown")
//                .exchange()
//                .expectStatus().isNotFound();

//        StepVerifier.create(repositoryFlux)
//                .expectSubscription()
//                .expectNextMatches(p->p.getName().equals("reactive"))
//                .verifyComplete();
    }

}
