package xyz.foobar.test;

import xyz.foobar.DiffException;
import xyz.foobar.DiffRenderer;
import xyz.foobar.Service.DiffEngineService;
import xyz.foobar.Diff;
import xyz.foobar.Service.DiffRenderService;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, DiffException {
        System.out.println("Heck");
        Person originalPerson = new Person();
        Person modifiedPerson = new Person();
        DiffRenderer renderer = new DiffRenderService();
        modifiedPerson.setFirstName("Vinay Kumar");
        originalPerson.setFirstName("Vinay");

        DiffEngineService serv = new DiffEngineService();
        Diff diff = serv.calculate(originalPerson, modifiedPerson);
        String val = renderer.render(diff);
        System.out.println(val);

    }
}
