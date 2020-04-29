package com.project.human.resource.repositories;

import com.project.human.resource.models.Cases;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional 
@Repository
public interface CaseRepository extends CrudRepository<Cases, Long>{
    ArrayList<Cases> getCasesByRequestedBy(String requestedBy);

    ArrayList<Cases> getCasesByAssignedTo(String assignedTo);

    Cases getCaseById(Long id);
}