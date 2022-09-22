package org.example.github.model.domain;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.annotations.OpenAPI30;

public class Branch {

    @Schema(description = "Name of the branch in a repository")
    private String name;

    @Schema(description = "SHA code of the latest commit in the branch")
    private String lastCommitSha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }

    public Branch(String name,String lastCommitSha){
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }

    public Branch(){
    }

}
