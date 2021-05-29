package xyz.foobar;

public class AttributeDiff {
    private String attributeName;
    private Object oldValue;
    private Object newValue;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public boolean compareOldToNew() {
        if (this.newValue.equals(this.oldValue)){
            return true;
        }
        return false;
    }
}
