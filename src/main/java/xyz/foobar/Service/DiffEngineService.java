package xyz.foobar.Service;

import xyz.foobar.AttributeDiff;
import xyz.foobar.Diff;
import xyz.foobar.DiffEngine;
import xyz.foobar.DiffException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DiffEngineService implements DiffEngine {
    public <T extends Serializable> T apply(T original, Diff<?> diff) throws DiffException {

        return null;
    }

    public <T extends Serializable> Diff<T> calculate(T original, T modified) throws DiffException {
        Diff<T> diff = new Diff<T>();
        String className = "";
        if(original!=null){
            className= original.getClass().getSimpleName();
        } else if(modified!=null){
            className = modified.getClass().getSimpleName();
        } else{
            return diff;
        }
        diff.setEntityName(className);

        List<AttributeDiff> attributeDiffList = new ArrayList<AttributeDiff>();
        if (original!=null && original.equals(modified)){
            return diff;
        } else if (original==null && modified!=null){
            diff.setCreated(true);
            Field []fields = modified.getClass().getDeclaredFields();
            for(Field field: fields){
                AttributeDiff attrDiff = new AttributeDiff();
                attrDiff.setAttributeName(field.getName());
                field.setAccessible(true);
                try{
                    attrDiff.setNewValue(field.get(modified));
                    attrDiff.setOldValue(null);
                    attributeDiffList.add(attrDiff);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else if (original!=null && modified==null){
            diff.setDeleted(true);
            Field []fields = original.getClass().getDeclaredFields();
            for(Field field: fields){
                AttributeDiff attrDiff = new AttributeDiff();
                attrDiff.setAttributeName(field.getName());
                field.setAccessible(true);
                try{
                    attrDiff.setNewValue(null);
                    attrDiff.setOldValue(field.get(original));
                    attributeDiffList.add(attrDiff);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }else{
            diff.setUpdated(true);
            Field []fields = original.getClass().getDeclaredFields();
            for(Field field: fields){
                AttributeDiff attrDiff = new AttributeDiff();
                attrDiff.setAttributeName(field.getName());
                field.setAccessible(true);
                try{
                    attrDiff.setNewValue(field.get(modified));
                    attrDiff.setOldValue(field.get(original));
                    attributeDiffList.add(attrDiff);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        diff.setAttributeDiffList(attributeDiffList);
        return diff;
    }
}
