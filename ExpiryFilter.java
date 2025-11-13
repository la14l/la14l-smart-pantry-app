import javax.swing.RowFilter;
import java.time.LocalDate;

public class ExpiryFilter extends RowFilter {
	
	@Override
	public boolean include(RowFilter.Entry entry) {
		
		LocalDate today = LocalDate.now();
		LocalDate fifteenDaysFromToday = today.plusDays(15);
		LocalDate expiryDate = LocalDate.parse(entry.getStringValue(6));
		return expiryDate.isBefore(fifteenDaysFromToday);
	}
}
