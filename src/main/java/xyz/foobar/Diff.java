package xyz.foobar;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * The object representing a diff.
 * Implement this class as you see fit. 
 *
 */
public class Diff<T extends Serializable> {

    private boolean IsCreated;
    private boolean IsDeleted;
    private boolean IsUpdated;
    private List<AttributeDiff> attributeDiffList;
    private String entityName;

    public boolean isCreated() {
        return IsCreated;
    }

    public void setCreated(boolean created) {
        IsCreated = created;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public boolean isUpdated() {
        return IsUpdated;
    }

    public void setUpdated(boolean updated) {
        IsUpdated = updated;
    }

    public List<AttributeDiff> getAttributeDiffList() {
        return attributeDiffList;
    }

    public void setAttributeDiffList(List<AttributeDiff> attributeDiffList) {
        this.attributeDiffList = attributeDiffList;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
