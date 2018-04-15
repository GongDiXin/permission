package com.mmall.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mmall.dao.SysDeptMapper;
import com.mmall.dto.DeptLevelDTO;
import com.mmall.model.SysDept;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/11 23:06
 */
@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper deptMapper;

    /**
     * 组装部门树
     *
     * @author GongDiXin
     * @date 2018/4/15 16:58
     * @return List<DeptLevelDTO>
    */
    public List<DeptLevelDTO> deptTree() {
        List<SysDept> deptList = deptMapper.getAllDept();
        List<DeptLevelDTO> dtoList = new ArrayList<>();
        for (SysDept dept : deptList) {
            dtoList.add(DeptLevelDTO.adapt(dept));
        }
        return deptListToTree(dtoList);
    }

    /**
     * 通过dto集合获取部门树
     *
     * @author GongDiXin
     * @date 2018/4/15 16:58
     * @param deptLevelList
     * @return List<DeptLevelDTO>
    */
    private List<DeptLevelDTO> deptListToTree(List<DeptLevelDTO> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return new ArrayList<>();
        }
        Multimap<String, DeptLevelDTO> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDTO> rootList = new ArrayList<>();
        for (DeptLevelDTO levelDto : deptLevelList) {
            levelDeptMap.put(levelDto.getLevel(), levelDto);
            if (levelDto.getLevel().equals(LevelUtil.ROOT)) {
                rootList.add(levelDto);
            }
        }
        Collections.sort(rootList, transformDeptTree);
        transformDeptTree(rootList, levelDeptMap);
        return rootList;
    }

    /**
     * 遍历计算所有部门树
     *
     * @author GongDiXin
     * @date 2018/4/15 22:45
     * @param deptLevelList
     *        当前层排序层
     * @param levelDeptMap
     *        所有部门
    */
    private void transformDeptTree(List<DeptLevelDTO> deptLevelList, Multimap<String, DeptLevelDTO> levelDeptMap) {
        // 拿到当前需要计算的层级 第一次计算的是第一层 即rootList
        // 传入levelDeptMap的原因是要根据level去获取下一层需要排序的所有部门集合
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 遍历该层的每个元素
            DeptLevelDTO levelDTO = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(levelDTO.getLevel(), levelDTO.getId());
            // 处理下一层
            List<DeptLevelDTO> tempDeptList = (List<DeptLevelDTO>)levelDeptMap.get(nextLevel);
            // 收敛条件
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, transformDeptTree);
                // 设置已经排好序的下一层部门
                levelDTO.setDeptList(tempDeptList);
                // 进入到下一层递归处理
                transformDeptTree(tempDeptList, levelDeptMap);
            }
        }
    }

    /**
     * 排序
     *
     * @author GongDiXin
     * @date 2018/4/15 22:50
     * @return Comparator<DeptLevelDTO>
    */
    private Comparator<DeptLevelDTO> transformDeptTree = new Comparator<DeptLevelDTO>() {
        @Override
        public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
            // 按照seq从小到大排序
            return o1.getSeq() - o2.getSeq();
        }
    };

}
