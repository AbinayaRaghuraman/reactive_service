package org.example.github.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Represents a repository")
public class Repository {

    @Schema(description = "Name of the repository")
    private String name;

    @Schema(description = "Owner name of the repository")
    private String ownerName;

    @Schema(description = "List of branches in the repository")
    private List<Branch> branches;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }


    public Repository(String name, String ownerName, List<Branch> branches) {
        this.name = name;
        this.ownerName = ownerName;
        this.branches = branches;
    }

    public Repository() {
    }
}
