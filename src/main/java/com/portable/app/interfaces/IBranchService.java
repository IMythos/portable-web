package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.entity.Branch;

public interface IBranchService {
    List<Branch> listBranches();
    Branch createBranch(Branch branch);
    void updateBranch(Branch branch);
    void deleteBranch(Integer branchId);
}
