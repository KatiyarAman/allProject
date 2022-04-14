package com.quokka.catalog.model;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UserDefinedType
public class HierarchyUDT {

    @Column(value = "hierarchy_list")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private List<String> hierarchyList;

    @Column(value = "active_flag")
    @CassandraType(type = Name.BOOLEAN)
        private boolean activeFlag;

    public HierarchyUDT(){
        super();
        this.hierarchyList = new ArrayList<String>();
        this.activeFlag = true;
    }

    public HierarchyUDT(HierarchyUDT hierarchy){
        super();
        if(hierarchy!=null && hierarchy.getHierarchyList().size()>0){
            this.hierarchyList = new ArrayList<String>(hierarchy.getHierarchyList());
            Collections.copy(this.hierarchyList, hierarchy.getHierarchyList());
        }else{
            this.hierarchyList = new ArrayList<String>();
        }
        this.activeFlag = hierarchy.isActiveFlag();
    }

    public List<String> getHierarchyList() {
        return hierarchyList;
    }

    public void setHierarchyList(List<String> hierarchyList) {
        this.hierarchyList = hierarchyList;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "HierarchyUDT [hierarchyList=" + hierarchyList + ", activeFlag=" + activeFlag + "]";
    }


}
