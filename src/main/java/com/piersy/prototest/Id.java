package com.piersy.prototest;

public class Id {
    private final int id;

    public Id(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id id1 = (Id) o;

        if (id != id1.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
