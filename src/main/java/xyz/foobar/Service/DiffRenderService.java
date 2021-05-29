package xyz.foobar.Service;

import org.w3c.dom.Attr;
import xyz.foobar.AttributeDiff;
import xyz.foobar.Diff;
import xyz.foobar.DiffException;
import xyz.foobar.DiffRenderer;

public class DiffRenderService implements DiffRenderer {

    private String renderValue;

    public String render(Diff<?> diff) throws DiffException {
        String renderString = "";
        String operation = getOperationString(diff);
        if (diff == null) {
            throw new DiffException();
        }
        if (diff.isCreated()) {
            renderString = RenderEntityCreatedString(renderString, diff);
            for (AttributeDiff attrDiff : diff.getAttributeDiffList()) {
                    renderString = RenderFieldChanges(renderString, operation, attrDiff);
            }
        } else if (diff.isDeleted()) {
            renderString = RenderEntityDeletedString(renderString, diff);
        } else if (diff.isUpdated()) {
            renderString = RenderEntityUpdatedString(renderString, diff);
            for (AttributeDiff attrDiff : diff.getAttributeDiffList()) {
                if (attrDiff.getNewValue()==null){
                    attrDiff.setNewValue("null");
                }
                if (attrDiff.getOldValue()==null){
                    attrDiff.setOldValue("null");
                }
                if (!attrDiff.getNewValue().equals(attrDiff.getOldValue())) {
                    renderString = RenderFieldChanges(renderString, operation, attrDiff);
                }
            }
        }
        return renderString;
    }

    public String getOperationString(Diff<?> diff) {
        if (diff.isCreated()) {
            return "create";
        } else if (diff.isUpdated()) {
            return "update";
        } else {
            return "delete";
        }
    }

    public String RenderEntityCreatedString(String renderString, Diff<?> diff) {
        return renderString.concat("Created:" + diff.getEntityName()).concat("\n");
    }

    public String RenderEntityDeletedString(String renderString, Diff<?> diff) {
        return renderString.concat("Deleted:" + diff.getEntityName()).concat("\n");
    }

    public String RenderEntityUpdatedString(String renderString, Diff<?> diff) {
        return renderString.concat("Updated:" + diff.getEntityName()).concat("\n");
    }

    public String RenderFieldChanges(String renderString, String operation, AttributeDiff attrDiff) {
        if (operation == "create") {
            String value = "";
            if (attrDiff.getNewValue() == null) {
                value = "null";
            } else {
                value = attrDiff.getNewValue().toString();
            }
            return renderString.concat("Create: " + attrDiff.getAttributeName() + " as " + value).concat("\n");
        } else if (operation == "update") {
            return renderString.concat("Update: " + attrDiff.getAttributeName() + " from " + attrDiff.getOldValue() + " to " + attrDiff.getNewValue()).concat("\n");
        }
        return renderString;
    }

    public String getRenderValue() {
        return renderValue;
    }

    public void setRenderValue(String renderValue) {
        this.renderValue = renderValue;
    }
}
