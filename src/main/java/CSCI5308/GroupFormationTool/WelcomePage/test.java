package CSCI5308.GroupFormationTool.WelcomePage;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationController;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        GroupFormationController controller = new GroupFormationController();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(129);
        list.add(60);
        List<Double> results = controller.normaliseWeights(list);
        results.stream().forEach(System.out::println);
    }
}
