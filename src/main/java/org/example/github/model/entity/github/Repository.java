package org.example.github.model.entity.github;

import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Repository {

    private String name;
    private Owner owner;
    private List<Branch> branches;
    private boolean fork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public org.example.github.model.domain.Repository toDomain() {
        final org.example.github.model.domain.Repository repository = new org.example.github.model.domain.Repository();
        repository.setName(this.name);
        repository.setOwnerName(Optional.ofNullable(this.owner.getLogin()).orElse(""));
        if (!CollectionUtils.isEmpty(this.branches)) {
            repository.setBranches(this.branches.stream()
                    .map(branch -> branch.toDomain())
                    .collect(Collectors.toList()));
        }
        return repository;
    }

}
