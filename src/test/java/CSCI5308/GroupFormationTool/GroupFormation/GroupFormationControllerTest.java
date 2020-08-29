package CSCI5308.GroupFormationTool.GroupFormation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupFormationControllerTest {

    @Test
    void parseComparisonChoices() {

    }

    @Test
    void normaliseWeights() {
        GroupFormationController controller = new GroupFormationController();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(129);
        list.add(60);
        controller.normaliseWeights(list);

    }
}