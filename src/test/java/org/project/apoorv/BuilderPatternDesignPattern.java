package org.project.apoorv;

import org.testng.annotations.Test;

public class BuilderPatternDesignPattern {
    public BuilderPatternDesignPattern Stag1()
    {
        System.out.println("Stag 1");
        return this;
    }
    public BuilderPatternDesignPattern Stag2(String str)
    {
        System.out.println("Stag 2 String is "+str);
        return this;
    }
    public BuilderPatternDesignPattern Stag3()
    {
        System.out.println("Stag 3");
        return this;
    }

    public static void main(String[] args) {

        BuilderPatternDesignPattern bp = new BuilderPatternDesignPattern();
        bp.Stag1().Stag2("test").Stag3();

    }

}
