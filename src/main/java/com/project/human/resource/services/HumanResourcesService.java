package com.project.human.resource.services;

import com.project.human.resource.domain.CasesException;
import com.project.human.resource.models.Cases;
import com.project.human.resource.models.User;
import com.project.human.resource.presentation.CaseRequestForm;
import com.project.human.resource.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class HumanResourcesService {
    @Autowired
    private CaseRepository caseRepository;

    public ArrayList<Cases> getMyCases(User userInfo) {
       
    	ArrayList<Cases> cases = new ArrayList<>();
        String role = userInfo.getRole();
        switch (role) {
            case "user":
                cases.addAll(caseRepository.getCasesByRequestedBy(userInfo.getName()));
                break;
            case "officer":
                cases.addAll(caseRepository.getCasesByRequestedBy(userInfo.getName()));
                cases.addAll(caseRepository.getCasesByAssignedTo(role));
                break;
            case "manager":
                cases.addAll(caseRepository.getCasesByAssignedTo(role));
                break;
        }
        return cases;
       
      
    }

    public void addNewCase(CaseRequestForm caseForm) throws CasesException {
        String caseType = caseForm.getType();
        Cases newCase;
        if(caseType.equals("offbcase") || caseType.equals("onbcase")) {
            newCase = caseForm.getOfficeNewCase();
        } else {
            newCase = caseForm.getUserNewCase();
        }
        try {
            caseRepository.save(newCase);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new CasesException("Sorry, it was impossible to save the new case!");
        }
    }

    public Cases getCase(String caseID) throws CasesException {
        Cases lookUpCase = caseRepository.getCaseById(Long.parseLong(caseID));
        if(lookUpCase == null) {
            throw new CasesException("Could not find the case with id "+ caseID +"!");
        }
        return lookUpCase;
    }

    public void addCaseComment(String caseID, String comment) throws CasesException {
        Cases editCase = caseRepository.getCaseById(Long.parseLong(caseID));
        if(editCase == null) {
            throw new CasesException("Could not find the case with id "+ caseID +"!");
        }
        editCase.setOfficerComment(comment);
        editCase.setAssignedTo("manager");
        try {
            caseRepository.save(editCase);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new CasesException("Sorry, it was impossible to add comment to case!");
        }
    }

    public void acceptCase(String caseID) throws CasesException {
        Cases editCase = caseRepository.getCaseById(Long.parseLong(caseID));
        if(editCase == null) {
            throw new CasesException("Could not find the case with id "+ caseID +"!");
        }
        editCase.setState("accepted");
        editCase.setAssignedTo("N/A");
        try {
            caseRepository.save(editCase);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new CasesException("Sorry, it was impossible to accept case!");
        }
    }

    public void declineCase(String caseID) throws CasesException {
        Cases editCase = caseRepository.getCaseById(Long.parseLong(caseID));
        if(editCase == null) {
            throw new CasesException("Could not find the case with id "+ caseID +"!");
        }
        editCase.setState("declined");
        editCase.setAssignedTo("N/A");
        try {
            caseRepository.save(editCase);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new CasesException("Sorry, it was impossible to decline case!");
        }
    }
}
