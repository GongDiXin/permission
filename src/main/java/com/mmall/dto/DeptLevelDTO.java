package com.mmall.dto;

import com.mmall.model.SysDept;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/11 22:58
 */
@ToString
public class DeptLevelDTO extends SysDept {

    private List<DeptLevelDTO> deptList;

    public List<DeptLevelDTO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptLevelDTO> deptList) {
        this.deptList = deptList;
    }

    public static DeptLevelDTO adapt(SysDept dept) {
        DeptLevelDTO dto = new DeptLevelDTO();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
