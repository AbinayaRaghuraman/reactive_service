package org.example.github.model.entity.github;

public class Branch {

    private String name;
    private Commit commit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public org.example.github.model.domain.Branch toDomain() {
        final org.example.github.model.domain.Branch domainBranch = new org.example.github.model.domain.Branch();
        domainBranch.setName(this.name);
        domainBranch.setLastCommitSha(this.commit.getSha());
        return domainBranch;
    }

}
