package cz.garkusha.jobstack.util;


import cz.garkusha.jobstack.model.Position;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garkusha on 24.3.2015.
 */
public class FindProbablyTheSamePositions {

    private static List<Position> probablyTheSamePositionList;

    public static List<Position> getProbablyTheSamePositionList() {
        return probablyTheSamePositionList;
    }

    public static boolean isProbablyTheSamePositionExist(ObservableList<Position> positions, Position filledPosition) {
        probablyTheSamePositionList = new ArrayList<>();
        for (Position position : positions) {
            if (position.getCompany().equals(filledPosition.getCompany())){
                probablyTheSamePositionList.add(position);
            }
        }
        return probablyTheSamePositionList.size() != 0;
    }
}
