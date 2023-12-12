##**Calendar Class in Android Studio**

**1. Creation of Calendar Instance:**
You can create a Calendar instance using the getInstance() method:

        Calendar calendar = Calendar.getInstance();

**2. Getting and Setting Date/Time Components:**
The Calendar class allows you to retrieve and set various components of a date, such as year, month, day, hour, minute, and second:

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH); // Note: Months are zero-based (0-11)
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

You can also set these components using the set method:

    calendar.set(Calendar.YEAR, 2023);

**3. Date/Time Manipulations:**
The add and roll methods are used for manipulating the date/time components:

    calendar.add(Calendar.DAY_OF_MONTH, 5); // Add 5 days to the current date
    calendar.roll(Calendar.MONTH, true); // Roll the month forward by one, doesn't affect other fields

**4. Formatting and Parsing:**
You can format the Calendar instance to a human-readable string using SimpleDateFormat:

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = sdf.format(calendar.getTime());

Conversely, you can parse a string to a Calendar object:

    String dateString = "2023-12-01 14:30:00";
    Date date = sdf.parse(dateString);
    calendar.setTime(date);

**5. Comparison:**

You can compare two Calendar instances using the compareTo method:

    Calendar otherCalendar = Calendar.getInstance();
    if (calendar.compareTo(otherCalendar) > 0) {
    // calendar is after otherCalendar
    } else if (calendar.compareTo(otherCalendar) < 0) {
    // calendar is before otherCalendar
    } else {
    // calendar and otherCalendar are equal
    }
