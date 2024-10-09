package co.edu.escuelaing.springsecurity.repository;

import co.edu.escuelaing.springsecurity.model.RestFull;

import java.util.List;

public interface RestFullRepository {

    List<RestFull> getAll();
}
