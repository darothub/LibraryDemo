package uk.gov.homeoffice.springtechtest.models.dto;

import java.util.Objects;

public class UserPayload {

    private Long id;

    private String name;

    public UserPayload(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserPayload(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPayload that = (UserPayload) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
