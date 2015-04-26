import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;;

public class Reminders {
	private PriorityQueue<Date, String> reminderQueue;
	private ArrayPriorityQueue<Date, Date> dateQueue;
	
	public Reminders() {
		this.reminderQueue = new ArrayPriorityQueue<Date, String>();
		this.dateQueue = new ArrayPriorityQueue<Date, Date>();
	}
	
	/**
	 * Adds a reminder at the given time
	 */
	public void setReminder(Date time, String reminder) {
		this.reminderQueue.insert(time, reminder);
		this.dateQueue.insert(time, time);
	}
	
	/**
	 * Retrieves & removes all reminders up to (and at) the given time
	 */
	public List<String> getReminders(Date currentTime) {
		List<String> reminders = new ArrayList<String>();
		
		while (!dateQueue.isEmpty() && (dateQueue.min().before(currentTime) || dateQueue.min().equals(currentTime))) {
			reminders.add(reminderQueue.removeMin());
			dateQueue.removeMin();
		}
		return reminders;
	}
	
	public static void main(String[] args) throws ParseException {
		Reminders test = new Reminders();
		
		// date format reminders
		try {
			Date time1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
			Date time2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 11:30:00");
			Date time3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 14:30:00");
			Date time4 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 18:00:00");
			Date time5 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 10:00:00");
			
			test.setReminder(time1, "Have breakfast");
			test.setReminder(time2, "Call Janet about carpet");
			test.setReminder(time3, "Pick up drycleaning");
			test.setReminder(time4, "Watch 'The Block' ");
			test.setReminder(time5, "Have a different breakfast");
			System.out.println(test.getReminders(time5));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
}
