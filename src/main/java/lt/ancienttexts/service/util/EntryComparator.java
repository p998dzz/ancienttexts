package lt.ancienttexts.service.util;

import lt.ancienttexts.domain.ListItem;

import java.util.Comparator;

public class EntryComparator implements Comparator<ListItem> {

    private int sortingOption;

    public EntryComparator(Integer sortingOption) {
        this.sortingOption = sortingOption;
    }

    @Override
    public int compare(ListItem o1, ListItem o2) {
        switch(sortingOption){
            case 1:
                return o1.getLocation().compareTo(o2.getLocation());
            case 2:
                return o1.getDateAdded().compareTo(o2.getDateAdded());
            case 3:
                return o2.getTitle().compareTo(o1.getTitle());
            case 4:
                return o2.getLocation().compareTo(o1.getLocation());
            case 5:
                return o2.getDateAdded().compareTo(o1.getDateAdded());
            default:
                return o1.getTitle().compareTo(o2.getTitle());
        }
    }
}
