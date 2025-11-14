package pantryAppPackage.filters;

import javax.swing.RowFilter;

//RowFilter class enables creation of filters which can then be applied to sorters to filter search results

public class LowStockFilter extends RowFilter {
    @Override
    public boolean include(RowFilter.Entry entry) {
        String qStr = entry.getStringValue(3);
        String tStr = entry.getStringValue(5);

        try {
            int quantity = Integer.parseInt(qStr);
            int threshold = Integer.parseInt(tStr);
            return quantity < threshold;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
