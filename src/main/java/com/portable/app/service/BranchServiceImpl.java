package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.entity.Branch;
import com.portable.app.interfaces.IBranchService;
import com.portable.app.repository.BranchRepository;

@Service
public class BranchServiceImpl implements IBranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Branch> listBranches() {
        return branchRepository.listBranches();
    }

    @Override
    @Transactional
    public Branch createBranch(Branch branch) {
        Integer newId = branchRepository.insertBranch(
            branch.getBranchName(),
            branch.getDistrict(),
            branch.getAddress()
        );
        
        branch.setBranchId(newId);
        return branch;
    }


    @Override
    @Transactional
    public void updateBranch(Branch branch) {
        branchRepository.updateBranch(
            branch.getBranchId(),
            branch.getBranchName(),
            branch.getDistrict(),
            branch.getAddress()
        );
    }


    @Override
    @Transactional
    public void deleteBranch(Integer branchId) {
        branchRepository.deleteBranch(branchId);
    }
}
