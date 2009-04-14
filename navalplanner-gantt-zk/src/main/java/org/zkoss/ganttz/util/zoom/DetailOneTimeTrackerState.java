/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.zkoss.ganttz.util.zoom;

import java.util.Collection;
import java.util.Vector;

import org.zkoss.ganttz.util.Interval;

/**
 * 
 * @author Francisco Javier Moran Rúa
 * 
 */
public class DetailOneTimeTrackerState extends TimeTrackerState {

    public static final DetailOneTimeTrackerState INSTANCE = new DetailOneTimeTrackerState();
    private static final int FIRST_LEVEL_ITEM_SIZE = 200;
    private static final int SECOND_LEVEL_ITEM_SIZE = 100;

    private DetailOneTimeTrackerState() {
    };

    private Collection<DetailItem> buildCollectionDetailsFirstLevel(
            int initialYear, int endYear) {

        Collection<DetailItem> detailsVector = new Vector<DetailItem>();

        for (int i = initialYear; i <= endYear; i++) {
            DetailItem d = new DetailItem(FIRST_LEVEL_ITEM_SIZE, String
                    .valueOf(i));
            detailsVector.add(d);
        }

        return detailsVector;
    }

    private Collection<DetailItem> buildCollectionDetailsSecondLevel(
            int initialYear, int endYear) {

        Collection<DetailItem> detailsVector = new Vector<DetailItem>();

        for (int i = initialYear; i <= endYear; i++) {

            DetailItem d1 = new DetailItem(SECOND_LEVEL_ITEM_SIZE, "H1");
            detailsVector.add(d1);

            DetailItem d2 = new DetailItem(SECOND_LEVEL_ITEM_SIZE, "H2");
            detailsVector.add(d2);

        }

        return detailsVector;
    }

    @Override
    protected Collection<DetailItem> createDetailsForFirstLevel(
            Interval interval) {
        int[] pairYears = calculateInitialEndYear(interval.getStart(), interval
                .getFinish());
        return buildCollectionDetailsFirstLevel(pairYears[0], pairYears[1]);

    }

    @Override
    protected Collection<DetailItem> createDetailsForSecondLevel(
            Interval interval) {
        int[] pairYears = calculateInitialEndYear(interval.getStart(), interval
                .getFinish());
        return buildCollectionDetailsSecondLevel(pairYears[0], pairYears[1]);
    }

    public Interval getRealIntervalFor(Interval interval) {
        int[] pairYears = calculateInitialEndYear(interval.getStart(), interval
                .getFinish());
        return new Interval(year(pairYears[0]), year(pairYears[1] + 1));
    }

}
